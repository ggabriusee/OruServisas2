Norint paleisti servisus reikia įvykdyti komandas:

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
  "footballteams": []
}
```

BENDRAVIMAS SU FUTBOLO KOMANDŲ SERVISU


INFORMACIJA APIE VISAS KOMANDAS
Informacija apie konkrecia komanda
Postman:
GET http://localhost:5000/locations/{id}/football_teams/{fid}

GET http://localhost:5000/locations/{id}/football_teams/{fid}?embedded=footballTeam

Be embedded, gražinama nuoroda į resursą, su embedded pats resursas 

Arba galima gauti futbolo komandą nenurodant id, tuomet, siejama pagal location id:

GET http://localhost:5000/locations/{id}/football_teams

GET http://localhost:5000/locations/{id}/football_teams?embedded=footballTeam


PRIDETI NAUJA KOMANDA
Postman:
POST http://localhost:5000/locations/{id}/football_teams
Ieiti i raw ir ivesti:
```JSON
{
  "Name": "New team"
}
```

PAKEISTI KOMANDOS ATRIBUTUS
Postman:
PUT http://localhost:5000/locations/{id}/football_teams/{fid}
Ieiti i raw ir ivesti :
```JSON
{
  "Captain": "Messi"
}
```

ISTRINTI KOMANDA
Postman:
DELETE http://localhost:5000/locations/{id}/football_teams/{fid}

