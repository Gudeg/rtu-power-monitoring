/* author: Yudhistira Arya <yudhistira.arya.rukmana@gmail.com>
*/

BEGIN; 
CREATE TABLE IF NOT EXISTS users (
    userid INTEGER UNIQUE,
    username TEXT NOT NULL UNIQUE, 
    firstname TEXT NOT NULL,
    lastname TEXT, 
    email TEXT NOT NULL UNIQUE, 
    password TEXT NOT NULL, 
    permission INTEGER NOT NULL, 

    PRIMARY KEY(userid, username, email)
);

/* TODO: put the userid to detect who modify the settings*/
CREATE TABLE IF NOT EXISTS rawdata (
    logid INTEGER, 
    rtuid INTEGER DEFAULT NULL, 
    rawdata TEXT, 
    md5hash TEXT DEFAULT NULL, 
    PRIMARY KEY(logid)
);

CREATE TABLE IF NOT EXISTS server_settings (
    remote_terminal_id INTEGER UNIQUE, 
    server_location TEXT, 
    server_time TEXT NOT NULL, 
    server_date TEXT NOT NULL, 
    delete_log_after INTEGER NOT NULL DEFAULT 30,
    setting_timestamp TEXT, 
    
    PRIMARY KEY(remote_terminal_id)
);

CREATE TABLE IF NOT EXISTS network_settings (
    setting_id INTEGER UNIQUE, 
    server_ip_address TEXT NOT NULL, 
    subnet_mask TEXT NOT NULL, 
    default_gateway TEXT NOT NULL, 
    dns_server1 TEXT, 
    dns_server2 TEXT,
    forwarding_config TEXT,
    setting_timestamp TEXT, 

    PRIMARY KEY(setting_id)
);

INSERT INTO users(username, firstname, lastname, email, 
    password, permission) 

/* temp pwd: 1234567 */
VALUES ("admin", "Yudhistira", "Arya", 
    "yudhistira.arya.rukmana@gmail.com", "b9d41f5f3847c78f74cfcc418a58550f6c535f31",
    , "63");

END; 
