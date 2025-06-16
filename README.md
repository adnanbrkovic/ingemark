# Ingemark zadatak

## Pokretanje aplikacije
- Napravio sam docker-compose koji pokreće aplikaciju u obliku docker kontejnera, odnosno kontejnera za Postgres, PgAdmin, i samu aplikaciju
- Pri pokretanju aplikacija se bilda u Docker image na osnovu multi stage Docker filea (koji prvo radi maven package, a zatim pokretanje jar-a)
- docker compose se pokreće sljedećom naredbom:
- **docker compose up --build**


## Dostupni url-ovi
Nakon pokretanja kontejnera dostupni su sljedeći url-ovi:

#### PgAdmin
- url http://localhost:5050/, podaci za pristup su admin@admin.com / admin
- nakon logina moguće je dodati pg server sa sljedećim podacima:
    - hostname: postgres
    - database: ingemark
    - user: ingemark

#### Swagger dokumentacija od aplikacije
- url http://localhost:8080/swagger-ui/index.html
- u repo sam dodao postman kolekciju sa 3 zadana endpointa (Ingemark postman collection) koja se može koristiti za testiranje funkcionalnosti endpointa
  (koristi se Basic autentifikacija sa accountom ingemark/ingemark)


### Ostale napomene
- pri izradi zadatka zbog jednostavnosti mnoge stvari su implementirane na jednostavniji način nego kakve bi trebale biti u produkciji
- inicijalizirao sam projekt preko Spring initializera sa potrebnimen osnovnim maven dependencijima
- korišten Hibernate kao ORM, bez auto-ddl opcije, inicijalna product tablica je kreirana putem flyway Skripte
- napravio sam ručne validacije parametara Producta, nisam koristio automatske JSR-380 validacije
- što se tiče domenskih objekata, zbog jednostavnosti koristio sam samo dva sloja - klasa Product predstavlja i dto i model, dok je ProductEntity korišten za repository sloj, te je napravljen Mapper u tu svrhu
- Repository - stavio sam da Spring data JPA automatski kreira implementaciju repo interfacea sa crud operacijama, nisam je ručno pravio
- Security - postavio sam jednostavni url security sa InMemoryUserDetails-om za zaštitu /product url-a
- napravljen je jednostavan test koji testira tražene tri servisne metode i mocka repo