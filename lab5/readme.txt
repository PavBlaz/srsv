U navedenom direktoriju nalazi se program za simuliranje komunikacije između procesa generatora i procesa poslužitelja. Prilikom pozivanja oba programa navode so po svakom 2 argumenta u obliku brojeva. Kod pozivanja programa poslužitelj prvi argument predstavlja broj radnih dretvi, dok drugi broj predstavlja ukupno trajanje posla da bi se dretve probudile. Prilikom pozivanja programa generatora prvi argument predstavlja broj poslova, dok drugi predstavlja trajanje svakog posla u sekundama.

Sa strane poslužitelja definiramo jednu dretvu koja zaprima poslove prema uputama iz zadatka. I radne dretve čiji broj smo definirali prvim argumentom pozivanjem programa poslužitelj. Radna dretva također radi po uputama iz zadatka.

Sa strane generatora stvaraju se opisnik posla koji sadrži jedinstveni identifikator, ukupno trajanje i ime zajedničkog spremnika u kojem se nalazi podatak za obradu. Prilikom samog kreiranja poslova generator zaključava ključ zajedničkog spremnika, zauzima memoriju za definirani broj poslova, te konačno i oslobađa ključ zajedničkog spremnika.

Za primjer rada prilikom branjenja ovog labosa poslužit ću se primjerom iz opisa zadatka laboratorijske vježbe, te on slijedi ovako:

./posluzitelj 5 30 & ./posluzitelj 2 20 & ./generator 5 20 & ./generator 3 10 