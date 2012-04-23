<?php

class User_model extends CI_Model { 

    public function __construct() {
        parent::__construct();
        $this->load->helper('ini');
    }

    public function authenticate($user_data) {

        $userid = trim($user_data['userid']);
        $password = substr(sha1(trim($user_data['password'])), 0, 10);

        $ini = parse_ini_file(USERS_INI_PATH, true);

        if (!strcmp($userid, $ini[$userid]['userid']) && 
            !strcmp($password, $ini[$userid]['password'])) {

            $this->session->set_userdata('userid', $ini[$userid]['userid']);
            $this->session->set_userdata('loggedin', true);
            $this->session->set_userdata('initial_ip', $_SERVER['REMOTE_ADDR']);
            $this->session->set_userdata('permission', $ini[$userid]['permission']);

            return true; 

        } else { 

            return false;

        }
    }

    public function check_session() {

        if (array_key_exists('loggedin', $this->session->userdata)) {
            $sess_status = $this->session->userdata['loggedin']; 
        } else {
            return false; 
        } 

        $prev_ip = $this->session->userdata['initial_ip'];
        $curr_ip = $_SERVER['REMOTE_ADDR'];

        if ($sess_status === true && 
            !strcmp($prev_ip, $curr_ip)) {

            return true; 

        } else {
        
            return false; 

        }
    }
}
