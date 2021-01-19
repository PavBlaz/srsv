  
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <mqueue.h>
#include <fcntl.h>

struct key_shared {
	pthread_mutex_t m;
	int id;
};

struct shared {
	int id;
	int broj;
	int zadatci[30];
};


#define SK_NAME  "/SRSV_LAB5"  /* created in /dev/shm/ */
#define SM_SIZE  sizeof (struct shared)
#define SK_SIZE  sizeof (struct key_shared)

#define MSG_QNAME    "/MSGQ_LAB5"
#define MSG_MAXMSGS  10
#define MSG_MAXMSGSZ 14

#define MSG_MSGSZ MSG_MAXMSGSZ /* must be at least MSG_MAXMSGSZ */

int main ( int argc,char *argv[] )
{
	int id, tmp, i, j, br_poslova, trajanje, r, policy;
	struct shared *share;
	struct key_shared *key;
	mqd_t mqdes;
	struct mq_attr attr;
	char *msg_ptr;
	size_t msg_len;
	unsigned msg_prio = 10;
	struct sched_param prio;
	
	policy = SCHED_RR;
	
	prio.sched_priority = 50;

	if ( pthread_setschedparam ( pthread_self(), policy,  &prio ) ) {
		perror ( "Error: pthread_setschedparam (root permission?)" );
		exit (1);
	}
	
	if(argc == 3){
		br_poslova = atoi(argv[1]);
		trajanje = atoi(argv[2]);
	}
	else{
		printf("Missing arguments!\n");
		return 0;
	}

	/* create/open shared memory object; map it to memory */
	id = shm_open ( SK_NAME, O_CREAT | O_RDWR, 00600 );
	if ( id == -1 || ftruncate ( id, SK_SIZE ) == -1) {
		perror ( "shm_open/ftruncate" );
		exit(1);
	}
	key = mmap ( NULL, SK_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, id, 0 );
	if ( key == (void *) -1) {
		perror ( "mmap" );
		exit(1);
	}
	close ( id );

	pthread_mutex_lock(&key->m);
	key->id += br_poslova;
	pthread_mutex_unlock(&key->m);
	
	attr.mq_flags = 0;
	attr.mq_maxmsg = MSG_MAXMSGS;
	attr.mq_msgsize = MSG_MAXMSGSZ;
	
	srand(time(NULL));
	
	for(i = (key->id - br_poslova); i < key->id; i++){
		/* create/open shared memory object; map it to memory */
		msg_ptr = (char *) malloc(MSG_MSGSZ * sizeof(char));
		sprintf(msg_ptr, "/SRSV_LAB5-%d", i);
		
		id = shm_open (msg_ptr , O_CREAT | O_RDWR, 00600 );
		if ( id == -1 || ftruncate ( id, SM_SIZE ) == -1) {
			perror ( "shm_open/ftruncate" );
			exit(1);
		}
		share = mmap ( NULL, SM_SIZE, PROT_READ | PROT_WRITE, MAP_SHARED, id, 0 );
		if ( share == (void *) -1) {
			perror ( "mmap" );
			exit(1);
		}
		close ( id );
		
		r = rand() % trajanje;
		r++;
		
		share->id = i;
		share->broj = r;
		
		for(j = 0; j < r; j++){
			share->zadatci[j] = rand() % 1000 + 1;
		}
		
		mqdes = mq_open ( MSG_QNAME, O_WRONLY | O_CREAT, 00600, &attr );
		if ( mqdes == (mqd_t) -1 ) {
			perror ( "producer:mq_open" );
			return -1;
		}
		if ( mq_send ( mqdes, msg_ptr, MSG_MSGSZ, msg_prio ) ) {
			perror ( "mq_send" );
			return -1;
		}
		
		printf ( "G: posao %d %d %s [ ", i, r, msg_ptr );
		for(j = 0; j < r; j++){
			printf( "%d ", share->zadatci[j] );
		}
		printf( "]\n" );
		
		sleep(1);
	}
}
