<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Form_model extends CI_Model { 

    public function __construct() {
        parent::__construct();
        $this->load->helper('ini');
    }

    public function set_account_form($json_data) {

        $userid = $this->session->userdata['userid'];

        $json_data['permission'] = !strcmp($userid, "admin") ? 
            ADMIN_LEVEL_PERMISSION : USER_LEVEL_PERMISSION;

        if (update_ini_file($userid, $json_data, USERS_INI_PATH)) {

        } else {

        }
    }

    public function set_network_form($json_data) {

        if (update_ini_file("network", $json_data, SERVERS_INI_PATH)) {

        } else { 

        }

    }

    public function set_firmwareupdate_form($json_data) {

        if (update_ini_file("firmware", $json_data, SERVERS_INI_PATH)) {

        } else { 

        }
    }

    public function set_cronjob_form($json_data) {

        if (update_ini_file("cron", $json_data, SERVERS_INI_PATH)) {

        } else { 

        }
    }

    public function set_portforwarding_form($json_data) {

        if (update_ini_file("forwarding", $json_data, SERVERS_INI_PATH)) {

        } else {

        }
    }

    public function set_time_ini($json_data) {
        $data['timezone'] =  $json_data['timezone'];
        $data['last_update'] = $json_data['last_update'];

        if (update_ini_file("time", $data, SERVERS_INI_PATH)) {

        } else {

        }
    }

    public function set_settings_form($json_data) {

        if (update_ini_file("settings", $json_data, SERVERS_INI_PATH)) {

        } else {

        }
    }
}
