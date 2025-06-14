# ingemark
ingemark task

Neke napomene
- pri izradi zadatka zbog jednostavnosti mnoge stvari su implementirane na jednostavniji način nego kakve bi trebale biti u produkciji
- korišten Hibernate kao ORM, bez auto-ddl opcije, inicijalna product tablica je kreirana putem flyway Skripte
- napravio sam ručne validacije parametara Producta, nisam koristio automatske JSR-380 validacije
- što se tiče domenskih objekata, zbog jednostavnosti koristio sam samo dva sloja - klasa Product predstavlja i dto i model, dok je ProductEntity korišten za repository sloj, te je napravljen Mapper u tu svrhu

