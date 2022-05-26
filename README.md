* Java 11
* Spring Boot 2.5
* Spring 5.3 (incl. Spring Web MVC, Spring Data JPA, Spring Actuator, Spring Validation and their dependencies)
* HyperSQL DB 2.5
* Lombok 1.18

-> a scheduled job that does a forward geocoding for all `district.name`
*. Limit the request rate to 1 request per second 
*. The API is not reliable and not all `district` can be geocoded.
*. Store the lat and lon values within the `district` for `category` of type `boundary`
*. The job starts working after the application is started  


-> The following REST Endpoints with JSON formatted responses:
*. `GET /districts` -> List of all `district.id`, `district.name`, `district.lat` and `district.lon`
*. `GET /districts/sorted` -> List of all `district` data with sorting and paging and related `vote.name`
, `vote.voteType` and `vote.pct`. 3. `GET /districts/{id}` -> Details of one given `district.id` including all data of
matching `district` and related `vote.name`, `vote.voteType` and `vote.pct`
*. `GET /votes` -> Unique list of all `vote.name`
*. `GET /votes?name={name}` -> Details of one given `vote.name` including all data of matching `vote` and
related `district.id`, `district.name`, `district.lat` and `district.lon`