# Services 
## Core

### Season

#### request: /season/

##### get All
            #request: / HTTP GET
            #response:
            [
              {
                "id": 1,
                "name": "uplne novy nazov"
              },
              {
                "id": 2,
                "name": "2012/2013"
              },
              {
                "id": 3,
                "name": "2013/2014"
              }
            ]
   
##### get One
            request: /{id} HTTP GET
            response:
            {
              "id": 1,
              "name": "uplne novy nazov",
              "seasonTournaments": [
                {
                  "id": 2,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "testick"
                },
                {
                  "id": 20,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "UPeCe Florbalová Liga"
                },
                {
                  "id": 1,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "season tournament upd"
                }
              ]
            }
                        
##### create
            #request: / HTTP POST
            {
              "name": "super Name"
            }
            #response:
            {
              "id": 333,
              "name": "super Name",
              "seasonTournaments": []
            }
            
##### edit
            #request: /{id} HTTP POST
              {
                "id": 1,
                "name": "uplne novy nazov"
              }
              
             #response: 
             {
              "id": 1,
              "name": "uplne novy nazov",
              "seasonTournaments": [
                {
                  "id": 19,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "meno zaujimave"
                },
                {
                  "id": 4,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "sameName"
                }
              ]
            }
             
##### delete
            request: /{id} HTTP DELETE
            response: same like get All without deleted item


### Tournament

#### request: /tournament/

##### get All
            #request: / HTTP GET
            #response:
           {
              "length": 8,
              "results": [
                {
                  "id": 1,
                  "name": "football"
                },
                {
                  "id": 23,
                  "name": "Balaa"
                },
                {
                  "id": 110,
                  "name": "PEter"
                }
              ]
            }
   
##### get One
            request: /{id} HTTP GET
            response:
            {
              "id": 1,
              "name": "football",
              "seasonTournaments": [
                {
                  "id": 3,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "Tests2"
                },
                {
                  "id": 4,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "name": "sameName"
                },
                {
                  "id": 17,
                  "seasonId": 1,
                  "tournamentId": 1,
                  "logo": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  }
              ],
              "length": 9
            }
                        
##### create
            #request: / HTTP POST
            {
              "name": "skuska tournament"
            }
            #response:
            {
              "id": 269,
              "name": "skuska tournament"
            }
            
##### edit
            #request: /{id} HTTP POST
              {
                "id": 1,
                "name": "uplne novy nazov"
              }
              
             #response: 
                          {
                "id": 1,
                "name": "uplne novy nazov"
              }
             
##### delete
            request: /{id} HTTP DELETE
            response: same like get All without deleted item

### SeasonTournamet 

#### request : /seasontournament/

##### find All
            # request: / HTTP GET
            # response: 
             {
               "length": 4,
               "results":    [
                   {
                     "id": 1,
                     "seasonId": 1,
                     "tournamentId": 1,
                     "name": "Tests"
                  },
                        {
                     "id": 2,
                     "seasonId": 1,
                     "tournamentId": 1,
                     "name": "testick"
                  },
                        {
                     "id": 3,
                     "seasonId": 1,
                     "tournamentId": 1,
                     "name": "Tests2"
                  },
                        {
                     "id": 4,
                     "seasonId": 1,
                     "tournamentId": 1,
                     "logo":          {
                        "id": 69,
                        "path": "cesta1"
                     },
                     "name": "novyNazov"
                  }
               ]
            }
##### find one
        # request: /{id} HTTP GET
        # response:
          {
             "id": 4,
             "seasonId": 1,
             "tournamentId": 1,
             "logo":    {
                "id": 69,
                "path": "cesta1"
             },
             "name": "novyNazov"
          }
          
          
###### create
          # request: / HTTP POST
             {
               "seasonId": 1,
               "tournamentId": 1,
               "logo":    {
                  "data": "VmFuMmFqejQ5NThwdA==",
                  "mimeType": "jpeg"
               },
               "name": "nameee"
            }

          # response:
            {
              "id": 22,
              "seasonId": 1,
              "tournamentId": 1,
              "logo": {
                "id": 1229,
                "path": "b5545467-2988-41f3-8a53-54f305a3def6.jpeg"
              },
              "name": "season tournament test"
            }

##### edit
        # request : /{id} HTTP POST
           {
               "id": 18,
               "seasonId": 1,
               "tournamentId": 1,
               "logo":    {
                  "id": 753
               },
               "name": "new Name"
            }

         # response:
           if success same like GET /18

##### delete
        # request: /{id} HTTP DELETE
        # response: if success same like find All operations result without deleted season tournament
        
        
### Registration

#### reguest: /registration/

##### team registration
            #request: / HTTP POST
            {
               "znak":    {
                  "data": "iVBORw0KGgoAAAANSUhEUgAAAbgAAAGxCAMAAAAnG1NJAAAAb1BMVEX///8AAAAAAAAAAAAAAACM/PkAAAAAAAAAAAAAAAD//",
                  "mimeType": "jpeg"
               },
               "seasonTournamentId": 4,
               "name": "bestTeam",
               "shortName": "bt",
               "color": "red",
               "isCancelled": false,
               "isVerify":false,
               "registrationPlayers":    [
                        {
                     "name": "Peter",
                     "surname": "Petko",
                     "birthDate": "2017-01-01", // treba dodrziavat tento format, este to budem riesit
                     "mail": "email@emal",
                     "phone": "phone",
                     "isStudent": true,
                     "sex": "male",
                     "isVerified":true,
                     "isProfessional": true,
                     "note": "note",
                     "number": 5,
                     "znak":    {
                  "data": "pJQDZW4xSvU+AXlkwmPqP/jT39sUq7hVuXMolzIA/mmcfILpWafRkhkt+RX/2Q==",
                  "mimeType": "jpeg"
               },
               "isCaptain": false
                  },
                        {
                     "name": "menoa",
                     "surname": "priaezvisko",
                     "birthDate": "2017-01-01",
                     "mail": "emaial",
                     "phone": "aphone",
                     "isStudent": true,
                     "sex": "male",
                     "isVerified":true,
                     "isProfessional": true,
                     "note": "note",
                     "number": 53,
                     "photo":    {
                  "data": "6ulLlZZQ4ApJQDZW4xSvU+AXlkwmPqP/jT39sUq7hVuXMolzIA/mmcfILpWafRkhkt+RX/2Q==",
                  "mimeType": "jpeg"
               },
               "isCaptain":true
                  }
               ]
            }
            
            #response:
            {
              "id": 69,
              "znak": {
                "id": 1231,
                "path": "f1dd6969-0462-4133-97e4-550f471a6c83.jpeg"
              },
              "seasonTournamentId": 4,
              "name": "bestTeam",
              "shortName": "bt",
              "color": "red",
              "isCancelled": false,
              "isVerify": false,
              "createdTime": "Mar 31, 2017 11:17:09 PM",
              "registrationPlayers": [
                {
                  "id": 130,
                  "registrationTeam": 69,
                  "name": "Peter",
                  "surname": "Petko",
                  "birthDate": "Jan 1, 2017 1:00:00 AM",
                  "mail": "email@emal",
                  "phone": "phone",
                  "isStudent": true,
                  "isVerified": true,
                  "sex": "male",
                  "isProfessional": true,
                  "note": "note",
                  "number": 5,
                  "isCaptain": false
                },
                {
                  "id": 129,
                  "registrationTeam": 69,
                  "name": "menoa",
                  "surname": "priaezvisko",
                  "birthDate": "Jan 1, 2017 1:00:00 AM",
                  "mail": "emaial",
                  "phone": "aphone",
                  "isStudent": true,
                  "isVerified": true,
                  "sex": "male",
                  "isProfessional": true,
                  "note": "note",
                  "number": 53,
                  "photo": {
                    "id": 1232,
                    "path": "6593fdca-d034-4225-a077-5e7f7283b946.jpeg"
                  },
                  "isCaptain": false
                }
              ]
            }
            
##### get all registration teams
            request: /team/ HTTP GET
            {
              "length": 22,
              "teams": [
                {
                  "id": 47,
                  "znak": {
                    "id": 1172,
                    "path": "e603e566-6225-4c83-82d4-e606f7733ed1.jpeg"
                  },
                  "seasonTournamentId": 4,
                  "name": "aaa",
                  "shortName": "a",
                  "color": "red",
                  "isCancelled": false,
                  "isVerify": false,
                  "createdTime": "Mar 28, 2017 10:13:02 PM",
                  "registrationPlayers": []
                },
                {
                  "id": 48,
                  "znak": {
                    "id": 1173,
                    "path": "ae3fe796-e129-4068-81c2-2745bdc38fc9.jpeg"
                  },
                  "seasonTournamentId": 17,
                  "name": "tiiiim",
                  "shortName": "a",
                  "color": "red",
                  "isCancelled": false,
                  "isVerify": false,
                  "createdTime": "Mar 28, 2017 10:14:00 PM",
                  "registrationPlayers": []
                },
                {
                  "id": 49,
                  "znak": {
                    "id": 1174,
                    "path": "9ad4371f-e72b-482c-8c7e-ff8f76ad087b.jpeg"
                  },
                  "seasonTournamentId": 4,
                  "name": "bestTeam",
                  "shortName": "bt",
                  "color": "red",
                  "isCancelled": false,
                  "isVerify": false,
                  "createdTime": "Mar 29, 2017 4:11:43 PM",
                  "registrationPlayers": []
                }
              ]
            }
            
##### get one registration team
            #request: /team/{id} HTTP GET
            response:
            {
              "id": 47,
              "znak": {
                "id": 1172,
                "path": "e603e566-6225-4c83-82d4-e606f7733ed1.jpeg"
              },
              "seasonTournamentId": 4,
              "name": "aaa",
              "shortName": "a",
              "color": "red",
              "isCancelled": false,
              "isVerify": false,
              "createdTime": "Mar 28, 2017 10:13:02 PM",
              "registrationPlayers": [
                {
                  "id": 85,
                  "registrationTeam": 47,
                  "name": "meno",
                  "surname": "priezvisko",
                  "birthDate": "Jan 1, 2017",
                  "mail": "gej",
                  "phone": "phone",
                  "isStudent": true,
                  "isVerified": true,
                  "sex": "male",
                  "isProfessional": true,
                  "note": "note",
                  "number": 5,
                  "photo": {
                    "id": 815,
                    "path": "040480dc-e858-415c-a23a-cc09aad9e82f.null"
                  },
                  "isCaptain": true
                },
                {
                  "id": 86,
                  "registrationTeam": 47,
                  "name": "menoa",
                  "surname": "priaezvisko",
                  "birthDate": "Jan 1, 2017",
                  "mail": "emaial",
                  "phone": "aphone",
                  "isStudent": true,
                  "isVerified": true,
                  "sex": "male",
                  "isProfessional": true,
                  "note": "note",
                  "number": 53,
                  "photo": {
                    "id": 815,
                    "path": "040480dc-e858-415c-a23a-cc09aad9e82f.null"
                  },
                  "isCaptain": true
                }
              ]
            }
            
##### edit registration team
            request: /team/{id} HTTP POST
            {
              "id": 47,
              "znak": {
                "id": 1172
              },
              "seasonTournamentId": 4,
              "name": "novy name",
              "shortName": "a",
              "color": "red",
              "isCancelled": false,
              "isVerify": false,
              "createdTime": "2017-03-18T10:13:02Z" // treba dodrzat format, este budem riesit
            }
            
            response:
            {
              "id": 47,
              "znak": {
                "id": 1172,
                "path": "e603e566-6225-4c83-82d4-e606f7733ed1.jpeg"
              },
              "seasonTournamentId": 4,
              "name": "novy name",
              "shortName": "a",
              "color": "red",
              "isCancelled": false,
              "isVerify": false,
              "createdTime": "Mar 18, 2017 11:13:02 AM",
              "registrationPlayers": []
            }

##### delete registration team
            request: /team/{id} HTTP DELETE
            response: same like get all registration teams without deleted item
            
##### get registration player
            request:/player/{id}
            response:
            {
              "id": 94,
              "registrationTeam": 51,
              "name": "Peter",
              "surname": "Petko",
              "birthDate": "Jan 1, 2017",
              "mail": "email@emal",
              "phone": "phone",
              "isStudent": true,
              "isVerified": true,
              "sex": "male",
              "isProfessional": true,
              "note": "note",
              "number": 5,
              "isCaptain": false
            }
            
##### edit registration player
            request: /player/{id} HTTP POST
            {
              "id": 94,
              "registrationTeam": 51,
              "name": "Peter editovany",
              "surname": "Petko",
              "birthDate": "2017-01-01",
              "mail": "email@emal",
              "phone": "phone",
              "isStudent": true,
              "isVerified": true,
              "sex": "male",
              "isProfessional": false,
              "note": "note",
              "number": 20,
              "isCaptain": true
            }
            response: same like request

##### delete registration player
            request:/player/{id} HTTP DELETE
            response: get all registration team response (neviem co by bolo idealne vraciat)
            

### Mapping

#### request : /mapping/
##### createMapping
            # request: /{idSeasonTournament}   
            # response:{}
            
##### request : /submit/
         # submitMappingPlayer
           # request:
           {
     "newPlayer":    {
        "id": 88,
        "registrationTeam": 48,
        "name": "pepko",
        "surname": "priaezvisko",
        "birthDate": "2017-01-01",
        "mail": "gej",
        "phone": "ooooooooo",
        "isStudent": true,
        "isVerified": true,
        "sex": "male",
        "isProfessional": true,
        "note": "note",
        "number": 53,
        "photo":       {
           "id": 815,
           "path": "040480dc-e858-415c-a23a-cc09aad9e82f.null"
        },
        "isCaptain": false
     },
    "competitorTeamId": 62
    }
             # response:  
             response:
    {
       "id": 491,
       "name": "pepko",
       "surname": "priaezvisko",
       "birthDate": "Jan 1, 2017 1:00:00 AM",
       "mail": "gej",
       "phone": "ooooooooo",
       "isStudent": true,
       "sex": "male"
    }
          
### Document service

#### request: /document/

##### upload image
            request: / HTTP POST
               {
                  "data": "iVBORw0KGgoAAAANSUhEUgAAAbgAAAGxCAMAAAAnG1NJAAAAb1BMVEX///8AAAAAAAAAAAAAAACM/PkAAAAAAAAAAAAAAAD/",
                  "mimeType": "jpeg"
               }
               
             response:
             {
              "id": 1233,
              "path": "0a95163d-92e3-483a-9597-5e2f1df7bf90.jpeg"
            }
##### load image
            request: /{path} HTTP POST
            response: IMAGE
            
##### delete image
            request: /{path} HTTP DELETE
            response: void (???)
