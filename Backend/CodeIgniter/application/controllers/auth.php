<?php 

class Auth extends CI_Controller {

    public function __construct() {

        parent::__construct();
        $this->load->model("user_model"); 
        $this->output->enable_profiler(false);

    }

    public function index() {
        $this->load->view('welcome_message');
    }

    public function check() {

        if ( $this->user_model->check_session() ) {

            $this->output->set_content_type("text/plain");
            $this->output->set_output($this->session->userdata['permission']);

        } else { 

            show_404();

        }
    }
    
    public function login() {

        $user_data = json_decode($this->input->post("auth"), true);

        if ( $this->user_model->authenticate($user_data) ) {

            $permission = $this->session->userdata("permission");

            $this->output->set_content_type("text/plain");
            $this->output->set_output($permission);

        } else { 

            show_404();

        }

    }

    public function logout() {

        $this->session->sess_destroy();

    }
}    
