<?php 

class Get extends CI_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->model(user_model);

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
        
    }
}
