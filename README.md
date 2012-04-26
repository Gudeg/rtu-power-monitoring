Remote Terminal Unit for Power Monitoring System 
================================================

Repository Structures
---------------------

* **master**
* **bugfix**
* **development**
* **development-bugfix**
* **client-primitive** (Non-JS capable client, planned)
* **client-primitive-bugfix**
* **client-primitive-development** (Non-JS capable client, planned)
* **client-primitive-development-bugfix** (Non-JS capable client, planned)

Config 
------

Each user is stored in .../model/users.ini: 

[admin]
userid = 
...

[AliBaba]
userid =
...

The most recent settings will be stored in .../model/server.ini: 

Design 
------

Form: 

* Before the form loaded, the form is filled with previous entry. The
  data is received from index.php/get/form/[form-name], eg:

    index.php/get/form/network-setting-form 
    index.php/get/form/port-forwarding-form

* Each form will post into something like /post/[form-name], eg:

    /index.php/post/networkSettingsForm

    /index.php/post/portForwardingForm

DataGrid request: 

    *DataGrid* widget will request from: 
        
        http://localhost:8080/index.php/get/paging?limit=30
