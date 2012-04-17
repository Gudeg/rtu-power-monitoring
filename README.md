Remote Terminal Unit for Power Monitoring System 
================================================

Repository Structures
---------------------

There will be several (still proposal) branches: 

* **master**
* **development**
* **client** (GWT)
* **client-primitive** (Non-js)
* **backend** (PHP)

**client**, **client-primitive**, and **backend** will merge into 
**development** while **development** will merge into **master** during 
release

**nodejs-backend** and **backbone-client** will merge into **bleeding-edge**.
It just for fun and practice. However if proved to be useful will be
merge into **development** and perhaps, **master**.

Table 
----- 

CREATE TABLE `components` (
  `ComID` int(5) NOT NULL AUTO_INCREMENT,
  `ComName` varchar(254) NOT NULL,
  PRIMARY KEY (`ComID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `log` (
  `logid` int(20) NOT NULL,
  `rtuid` int(11) DEFAULT NULL,
  `logdate` datetime DEFAULT NULL,
  `refid` int(7) DEFAULT NULL,
  `logrecvtime` datetime DEFAULT NULL,
  PRIMARY KEY (`logid`),
  KEY `rtuid` (`rtuid`),
  KEY `logdate` (`logdate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `logvaluecurrent` (
  `logvalid` int(20) NOT NULL AUTO_INCREMENT,
  `logid` int(20) DEFAULT NULL,
  `logrtuid` int(11) DEFAULT NULL,
  `paramid` int(5) DEFAULT NULL,
  `paramvalue` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`logvalid`),
  KEY `logid` (`logid`),
  KEY `rtuid` (`logrtuid`),
  KEY `paramid` (`paramid`)
) ENGINE=InnoDB AUTO_INCREMENT=1923601 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `logvaluehistory` (
  `logvalid` int(20) NOT NULL AUTO_INCREMENT,
  `logid` int(20) DEFAULT NULL,
  `logrtuid` int(11) DEFAULT NULL,
  `paramid` int(5) DEFAULT NULL,
  `paramvalue` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`logvalid`),
  KEY `logid` (`logid`),
  KEY `logrtuid` (`logrtuid`),
  KEY `paramid` (`paramid`)
) ENGINE=InnoDB AUTO_INCREMENT=1923601 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `nodes` (
  `nodeid` int(11) NOT NULL AUTO_INCREMENT,
  `nodename` varchar(30) DEFAULT NULL,
  `nodeparentid` int(11) DEFAULT '0',
  `nodetype` int(3) DEFAULT NULL,
  PRIMARY KEY (`nodeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `nodetype` (
  `nodetypeid` tinyint(3) NOT NULL AUTO_INCREMENT,
  `nodetypename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`nodetypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `rawdata` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `rtuid` int(5) DEFAULT NULL,
  `rawdata` text,
  `md5hash` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`logid`),
  KEY `rtuid` (`rtuid`),
  KEY `md5hash` (`md5hash`)
) ENGINE=InnoDB AUTO_INCREMENT=24046 DEFAULT CHARSET=latin1;


CREATE TABLE `parameter` (
  `parameterID` int(11) NOT NULL AUTO_INCREMENT,
  `parameterName` text NOT NULL,
  PRIMARY KEY (`parameterID`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;

CREATE TABLE `rtu` (
  `rtuid` int(11) NOT NULL AUTO_INCREMENT,
  `rtuname` varchar(50) DEFAULT NULL,
  `rtucode` varchar(30) DEFAULT NULL,
  `rtupwd` varchar(30) DEFAULT NULL,
  `rtulevel` int(3) DEFAULT NULL,
  `rtusite` int(11) DEFAULT NULL,
  `rtulastupdate` datetime DEFAULT NULL,
  PRIMARY KEY (`rtuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `trails` (
  `trailid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `operation` varchar(254) COLLATE latin1_general_ci DEFAULT NULL,
  `reftable` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  `refid` varchar(30) COLLATE latin1_general_ci DEFAULT NULL,
  PRIMARY KEY (`trailid`),
  KEY `userid` (`userid`),
  KEY `operation` (`operation`),
  KEY `refid` (`refid`),
  KEY `reftable` (`reftable`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `fullname` varchar(254) NOT NULL,
  `active` int(1) NOT NULL DEFAULT '0',
  `userpwd` varchar(254) NOT NULL DEFAULT '',
  `usertype` varchar(16) NOT NULL,
  `userentity` int(3) NOT NULL DEFAULT '0',
  `islimited` tinyint(1) NOT NULL DEFAULT '0',
  `isadmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;

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

DataGrid request: 

    *DataGrid* will request from: 
        
        http://localhost:8080/get/paging?limit=30

    *Nginx* will translate into: 

        http://localhost:8000/index.php/get/paging/30 

Secret 
------

There are several repositories that is made for fun only:

* **bleeding-edge**
* **nodejs-backend** (optional)
* **backbone-client** (optional)
