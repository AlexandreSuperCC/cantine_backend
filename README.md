# cantine application
## run
* construct the database with file/mo_cantine.sql
* starting port: 8084/8085 in the file application-dev/prod.yml
## technique point
### keywords
* cloud
* hibernate
* websocket
* mvc
* springboot vue.js nginx
* token 
* md5
* design pattern:
    * builder
    * singleton
    * template
### details
* deploy with nginx 
* use websocket to realize the full-duplex communication between server-client
* use architecture mvc -> controller/model/dao
* use the restful style API
* make the same code into component to avoid repeating myself
* restore the password into database using md5 encryption for security purpose
* use token to make security the communication with the hardwares
* use token to make security the session; after 30 minutes inactive status, ask you if you want to expand the session; anyhow expired after 1 hour
* use qiniuyun cloud service to upload the file in my personal cloud
* test service helps simulate the data in the canteen
* 404 page not found, 403 page not authorized
* up to top button in the index
