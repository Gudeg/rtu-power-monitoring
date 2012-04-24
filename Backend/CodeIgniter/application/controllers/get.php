<?php 

class Get extends CI_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model("user_model");

        if ($this->user_model->check_session()) {

            $this->output->enable_profiler(false);

        } else { 

            show_404();

        }
    }

    public function index() {
        $this->load->view('welcome_message');
    }

    private function return_json_output($json_data) {
        $this->output->set_content_type('application/json');
        $this->output->set_output(json_encode($json_data));
    }

    public function paging($limit = 30) {

        $json_data = $limit;
        $this->return_json_output($json_data);

    }

    public function form($form_name) {
        // request from previous value from db 
        if ( equals($form_name, "network-form") ) {

            $this->return_json_from_ini("network");

        } else if ( equals($form_name, "portforwarding-form") ) {

            $this->return_json_from_ini("forwarding");

        } else if ( equals($form_name, "account-form") ) {

            $userid = $this->session->userdata['userid'];
            $this->return_json_from_ini($userid, USERS_INI_PATH);

        } else if ( equals($form_name, "datetime-form") ) {

            $data = parse_ini_file(SERVERS_INI_PATH, true); 

            $time_data = $data['time'];
            $time_data["server_time"] = exec("date");

            $this->output->set_content_type("application/json");
            $this->output->set_output(json_encode($time_data));

        } else if ( equals($form_name, "firmwareupdate-form") ) {

            $this->return_json_from_ini("firmware");

        } else if ( equals($form_name, "cronjob-form") ) {

            $this->return_json_from_ini("cron");

        } else if ( equals($form_name, "settings-form") ) {

            $this->return_json_from_ini("settings");

        } else {

            show_404();

        }
    }

    private function return_json_from_ini($type, $ini=SERVERS_INI_PATH){

        $this->output->set_content_type("application/json");
        $this->output->set_output(parse_ini_to_json(
            $ini, $type));
    }
}
