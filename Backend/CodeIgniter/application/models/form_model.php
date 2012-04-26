<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

require_once("config_update.php");

class Form_model extends CI_Model { 

    public function __construct() {
        parent::__construct();
        $this->load->helper('ini');
    }

    public function set_account_form($json_data) {

        $userid = $this->session->userdata['userid'];

        $json_data['permission'] = !strcmp($userid, "admin") ? 
            ADMIN_LEVEL_PERMISSION : USER_LEVEL_PERMISSION;

        update_ini_file($userid, $json_data, USERS_INI_PATH);
    }

    public function set_network_form($json_data) {

        update_ini_file("network", $json_data, SERVERS_INI_PATH);

    }

    public function set_firmwareupdate_form($json_data) {

        update_ini_file("firmware", $json_data, SERVERS_INI_PATH);
    }

    public function set_cronjob_form($json_data) {
        
        $cronUpdater = new CronSettings();
        if ( ! $cronUpdater->update($json_data['crontab'])) {

            return false;

        } 
        return update_ini_file("cron", $json_data, SERVERS_INI_PATH);
    }

    public function set_portforwarding_form($json_data) {

        $rinetdUpdater = new RinetdSettings();
        if ( ! $rinetdUpdater->update($json_data[port_forwarding_config])) {
            
            return false; 

        }
        return update_ini_file("forwarding", $json_data, SERVERS_INI_PATH);
    }

    public function set_time_ini($json_data) {
        $data['timezone'] =  $json_data['timezone'];
        $data['last_update'] = $json_data['last_update'];

        if (! time_sync($json_data) ) {
            return false;
        }

        update_ini_file("time", $data, SERVERS_INI_PATH);
    }

    public function set_settings_form($json_data) {

        update_ini_file("settings", $json_data, SERVERS_INI_PATH);

    }

    private function time_sync($json_data) {

        $dateval = $json_data['unix_time'];

        $data = "#!/bin/sh
        /bin/date -s $dateval > /dev/null 2 >&1
        /sbin/hwclock --systohc
        ";

        print_r($data);

        rw_timer();
        if ( ! $handle = fopen(SCRIPT_FILE, "w+") ) {
            return false; 
        }

        fwrite($handle, $data); 
        fclose($handle):
        return true;
    }
}
