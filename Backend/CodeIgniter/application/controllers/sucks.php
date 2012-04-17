<?php 

class Sucks extends CI_Controller {
    private $json_data;

    public function __construct() {
        parent::__construct();
        $this->output->enable_profiler(true);
    }

    public function index() {
        $this->load->view('welcome_message');
    }

    private function return_json_output() {
        $this->output->set_content_type('application/json');
        $this->output->set_output(json_encode($json_data));
    }

    public function paging($limit = 30) {

        $json_data = $limit;

        return_json_output();
    }

    public function previous($form_name) {
        // request from previous value from db 
        
        $json_data = $form_name;

        return_json_output();
    }
}
