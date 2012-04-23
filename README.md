Remote Terminal Unit for Power Monitoring System 
================================================

TODO:
-----

* Write error helper 

Repository Structures
---------------------

There will be several (still proposal) branches: 

* **master**
* **development**
* **client** (GWT)
* **client-primitive** (Non-js)
* **backend** (PHP)
* **DB**

*client*, *client-primitive*, and *backend* will merge into 
development* while *development* will merge into *master* during 
release

*nodejs-backend* and **backbone-client** will merge into *bleeding-edge*.
It just for fun and practice. However if proved to be useful will be
merge into **development** and perhaps, *master*.

Config 
------

user.ini: 

userid = 
firstname =
lastname = 
email = 
password = "b9d41f5f3847c78f74cfcc418a58550f6c535f31"
permission = 
last_session = 

firstname = 
lastname = 
email = 
password =
permission = 

server.ini: 

[server]
remote_terminal_id = 
server_location = 

[time]
server_time =
server_date =
delete_log_after = 

[network]
server_ip_address = 
subnet_mask = 
default_gateway = 
dns_server1 = 
dns_server2 = 

[forwarding]
port_forwarding_config = 

[cron]
crontab = 

[firmware] 
firmware_name =
version =
comment = 

Design 
------

Form: 
* Before the form loaded, the form is filled with previous entry. The
  data is received from. 

    /get/prev/network-setting-form 
    /get/prev/port-forwarding-form

    which nginx will 

* Each form will post into something like /post/widget-name. Eg:

    /post/network-setting-form
        -> /index.php/post/networkSettingsForm

    /post/portforwarding-form
        -> /index.php/post/portForwardingForm

* Table (eg. Account Form): 

    username (fk, pk), firstname(fk), lastname(fk), email(fk), 
    password(fk), entry-date

DataGrid request: 

    *DataGrid* will request from: 
        
        http://localhost:8080/get/paging?limit=30

    *Nginx* will translate into: 

        http://localhost:8000/index.php/get/paging/30 

Secret 
------

There are several repositories that is made for fun only:

* **bleeding-edge**
* **nodejs-backend** 
* **backbone-client** 
* **mongodb** 
