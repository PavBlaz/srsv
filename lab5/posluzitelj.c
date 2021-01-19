
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <mqueue.h>
#include <fcntl.h>
#include <time.h>
#include <signal.h>
#include <sched.h>

/*! List element pointers */
typedef struct _list_h_
{
	struct _list_h_  *prev;
			  /* pointer to previous list element */

	struct _list_h_  *next;
			  /* pointer to next list element */

	void 		 *object;
			  /* pointer to object (which contains this list_h) */
}
list_h;

/*! list header type */
typedef struct _list_
{
	list_h  *first;
	list_h  *last;
}
list_t;

/* for static list elements initialization */
#define LIST_H_NULL	{ NULL, NULL, NULL }

/* for static list initialization (empty list) */
#define LIST_T_NULL	{ NULL, NULL }

#define FIRST	0	/* get first or last list element */
#define LAST	1

void list_init ( list_t *list );

/*! Add element to list, add to tail - as last element */
void list_append ( list_t *list, void *object, list_h *hdr );


/*! Get pointer to first or last list element */
void *list_get ( list_t *list, unsigned int flags );

/*!
 * Remove element from list: FIRST, LAST or given (ref)
 * NOTE function assumes that ref element is in list - it doesn't check!!!
 */
void *list_remove ( list_t *list, unsigned int flags, list_h *ref );



struct shared {
	int id;
	int broj;
	int zadatci[30];
};

struct shared_list {
	list_h list;
	char *poslovi;
};

static 	list_t lista;

static sem_t sem;
static pthread_mutex_t m;

int br_poslova;
int iter = 100000;
pthread_cond_t punoPosla = PTHREAD_COND_INITIALIZER;
int zavrsi = 0;

#define SK_NAME  "/SRSV_LAB5"  /* created in /dev/shm/ */
#define SM_SIZE  sizeof (struct shared)
#define SK_SIZE  sizeof (struct key_shared)

#define MSG_QNAME    "/MSGQ_LAB5"
#define MSG_MAXMSGS  10
#define MSG_MAXMSGSZ 14

#define MSG_MSGSZ MSG_MAXMSGSZ /* must be at least MSG_MAXMSGSZ */


static void signal_handler( int sig, siginfo_t *info, void *context )
{
	if(sig == SIGTERM){
	    zavrsi = 1;
	    printf("Kraj!\n");
	}
}

void *radna_dretva (void *p)
{
	int *n = p;
	int i, j, id;
	struct shared_list *share;
	struct shared *posao;
	
	pthread_cond_wait(&punoPosla, &m);
	
	while(1){
		sem_wait(&sem);
		share = list_remove(&lista, FIRST, NULL);
		sem_post(&sem);
		
		if(share != NULL){
			id = shm_open (share->poslovi , O_CREAT | O_RDWR, 00600 );
			if ( id == -1 || ftruncate ( id, SM_SIZE ) == -1) {
				perror ( "shm_open/ftruncate" );
				exit(1);
			}
			posao = mmap ( NULL, SM_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, id, 0 );
			if ( share == (void *) -1) {
				perror ( "mmap" );
				exit(1);
			}
			close ( id );
			
			for(i = 0; i < posao->broj; i++){
				printf("R%d: id:%d obrada podatka: %d (%d/%d)\n", *n, posao->id, posao->zadatci[i], i + 1, posao->broj);
				for (j = 0; j < iter; j++){
					asm volatile ("":::"memory");
				}
			}
			
			munmap ( posao, SM_SIZE );
			shm_unlink ( share->poslovi );
			
			br_poslova--;
		}
		else if(zavrsi){
			return p; //ili pthread_exit(p);
		}
		else{
			printf("R%d: nema poslova, spavam\n", *n);
		}
		
		if(!zavrsi){
			pthread_cond_wait(&punoPosla, &m);
		}
	}

	return p; //ili pthread_exit(p);
}

int main (int argc,char *argv[])
{
	pthread_t *t;
	int br_dretvi, i, j, cekanje, policy;
	int *num, *status;
	mqd_t mqdes;
	char *msg_ptr;
	size_t msg_len;
	unsigned msg_prio;
	struct shared_list *podatak;
	struct timespec start, stop, timeout;
	float total;
	struct sched_param prio;
	pthread_attr_t attr;
	struct sigaction act;
	
	policy = SCHED_RR;
	
	prio.sched_priority = 60;

	if ( pthread_setschedparam ( pthread_self(), policy,  &prio ) ) {
		perror ( "Error: pthread_setschedparam (root permission?)" );
		exit (1);
	}
	
	if(argc == 3){
		br_dretvi = atoi(argv[1]);
		cekanje = atoi(argv[2]);
		t = (pthread_t *) malloc(br_dretvi * sizeof(pthread_t));
		num  = (int *) malloc(br_dretvi * sizeof(int));
	}
	else{
		printf("Missing arguments!\n");
		return 0;
	}
	
	pthread_attr_init ( &attr );
	pthread_attr_setinheritsched ( &attr, PTHREAD_EXPLICIT_SCHED );
	pthread_attr_setschedpolicy ( &attr, policy );
	prio.sched_priority = 40;
	pthread_attr_setschedparam ( &attr, &prio );
	
	act.sa_sigaction = signal_handler;
	sigemptyset ( &act.sa_mask );
	sigaddset ( &act.sa_mask , SIGTERM );
	act.sa_flags = SA_SIGINFO;
	sigaction ( SIGTERM, &act, NULL );
	
	sem_init(&sem, 0, 1);
	
	list_init(&lista);
	
	br_poslova = 0;
	
	while(total < 1.0){
		iter *= 2;
		
		if( clock_gettime( CLOCK_MONOTONIC, &start) == -1 ) {
			perror( "clock gettime" );
			exit( EXIT_FAILURE );
		}
		for (i = 0; i < iter; i++){
			asm volatile ("":::"memory");
		}
		if( clock_gettime( CLOCK_MONOTONIC, &stop) == -1 ) {
			perror( "clock gettime" );
			exit( EXIT_FAILURE );
		}
		
		total = 1.0 * ( stop.tv_sec - start.tv_sec ) + ( stop.tv_nsec - start.tv_nsec )/1000000000.0 + total;
	}
	
	iter = iter/total;
	total = 0.0;
	
	for(i = 0; i < br_dretvi; i++){
		num[i] = i;
		pthread_create(&t[i], &attr, radna_dretva, (void *) &num[i]);
	}
	
	mqdes = mq_open ( MSG_QNAME, O_RDONLY );
	if ( mqdes == (mqd_t) -1 ) {
		perror ( "consumer:mq_open" );
		return -1;
	}
	
	printf("P: pokrecem zaostale poslove (nakon isteka vise od %d sekundi)\n", cekanje);
	
	while(1){
		if( clock_gettime( CLOCK_MONOTONIC, &start) == -1 ) {
			perror( "clock gettime" );
			exit( EXIT_FAILURE );
		}
		
		if( clock_gettime( CLOCK_REALTIME, &timeout) == -1 ) {
			perror( "clock gettime" );
			exit( EXIT_FAILURE );
		}
		
		timeout.tv_sec += 1.0;
		
		msg_ptr = (char *) malloc(MSG_MSGSZ * sizeof(char));
		msg_len = mq_timedreceive ( mqdes, msg_ptr, MSG_MSGSZ, &msg_prio, &timeout );
		if ( msg_len < 0 ) {
			perror ( "mq_receive" );
			return -1;
		}
		else if(strstr(msg_ptr, "/SRSV_LAB5") != NULL){
			printf ( "P: zaprimio %s \n", msg_ptr );
			podatak = (struct shared_list *) malloc(sizeof(struct shared_list));
			podatak->poslovi = msg_ptr;
			list_append(&lista, podatak, &podatak->list);
			
			br_poslova++;
		}
		
		if( clock_gettime( CLOCK_MONOTONIC, &stop) == -1 ) {
			perror( "clock gettime" );
			exit( EXIT_FAILURE );
		}
		
		total = 1.0 * ( stop.tv_sec - start.tv_sec ) + ( stop.tv_nsec - start.tv_nsec )/1000000000.0 + total;
		
		if(br_poslova >= br_dretvi || total >= cekanje){
			total = 0.0;
			pthread_cond_broadcast(&punoPosla);
		}
		
		if(zavrsi){
			break;
		}
	}
	
	for(i = 0; i < br_dretvi; i++){
		pthread_join(t[i], (void *) &status);
		printf("Got status: %d\n", *status);
	}

	return 0;
}

void list_init ( list_t *list )
{
	list->first = list->last = NULL;
}

/*! Add element to list, add to tail - as last element */
void list_append ( list_t *list, void *object, list_h *hdr )
{
	hdr->object = object; /* save reference to object */
	hdr->next = NULL; /* put it at list end (as last element) */

	if ( list->first )
	{
		list->last->next = hdr;
		hdr->prev = list->last;
		list->last = hdr;
	}
	else {
		list->first = list->last = hdr;
		hdr->prev = NULL;
	}
}


/*!
 * Remove element from list
 * \param list	List identifier (pointer)
 * \param flags	Constant: FIRST(0) or LAST(1) - which element to remove from
 *		list and return pointer to it
 * \param ref	Reference (pointer) to element to be removed from list
 * \return pointer to removed list element, NULL if list is empty
 * NOTE function assumes that element is in list - it doesn't check!!!
 */
void *list_remove ( list_t *list, unsigned int flags, list_h *ref )
{
	list_h *hdr;

	if ( ref )
		hdr = ref;
	else if ( flags & LAST )
		hdr = list->last;
	else
		hdr = list->first;

	if ( hdr )
	{
		if ( hdr->prev )
			hdr->prev->next = hdr->next;

		if ( hdr->next )
			hdr->next->prev = hdr->prev;

		if ( list->first == hdr )
			list->first = hdr->next;

		if ( list->last == hdr )
			list->last = hdr->prev;

		return hdr->object;
	}
	else {
		return NULL;
	}
}
