Norint paleisti servisus reikia paleisiti komandas:

`docker-compose build`

`docker-compose up -d`

ORU SERVISAS

Servisas pasiekiamas: http://localhost:5000/locations
Šiuo adresu pasiekiami visų lokacijų duomenys

Gauti visas lokacijas pagal miestą su get metodu : http://localhost:5000/locations/byCity/{name_of_city}

Gauti visas konkrečią lokaciją pagal id su get metodu : http://localhost:5000/locations/{location_ID}

Ištrinti lokaciją pagal nurodytą ID su delete metodu: http://localhost:5000/locations/{location_ID}

Atnaujinti lokaciją pagal nurodytą ID su put metodu: http://localhost:5000/locations/{location_ID}


body nurodyti, duomenų struktūrą pvz:
```JSON
{
  "temperature": 19,
  "city": "Vilnius",
  "date": "2018-02-05"
  "footballTeams": []
}
```

Pridėti lokacija su post metodu : http://localhost:5000/locations/ 


body nurodyti, duomenų struktūrą pvz:
```JSON
{
  "temperature": -20,
  "city": "Praha",
  "date": "2018-12-31"
  "footballTeams": []
}
```

BENDRAVIMAS SU FUTBOLO KOMANDŲ SERVISU


INFORMACIJA APIE VISAS KOMANDAS


Informacija apie konkrecia komanda
Postman:
GET http://localhost:81/locations/{id}/football_teams , kur id musu atveju nuo 1 iki 5

PRIDETI NAUJA KOMANDA
Postman:
POST http://localhost:81/locations/{id}/football_teams, kur id nuo 1 iki 5.
Ieiti i raw ir ivesti:
{
  "Name": "New team"
}


PAKEISTI KOMANDOS ATRIBUTUS
Postman:
PUT http://localhost:81/locations/{id}/football_teams, kur id nuo 1 iki 5.
Ieiti i raw ir ivesti :
{
  "Captain": "Messi"
}

ISTRINTI KOMANDA
Postman:
DELETE http://localhost:81/locations/{id}/football_teams , kur id nuo 1 iki 6

