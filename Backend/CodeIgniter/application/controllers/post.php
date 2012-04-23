<?php 

class Post extends CI_Controller {

    public function __construct() {

        parent::__construct();
        $this->load->model("user_model");

        if ($this->user_model->check_session()) {

            $this->load->model("form_model");
            $this->output->enable_profiler(false);

        } else { 

            $this->error_not_authenticate();

        }

    }

    private function equals($str1, $str2) {
        if (strcmp($str1, $str2) === 0) {
            return true; 
        } else { 
            return false; 
        }
    }

    public function upload($form_name) { 

        $base_uload_dir = APPPATH . "models/uploads/";

        if ( $this->equals($form_name, "firmwareupdate-form") ) {

            $config['upload_path'] = $base_uload_dir . "firmware";
            $config['overwrite'] = true;
            $config['allowed_types'] = "txt|php";

            $this->load->library('upload', $config);

            $upload_path = $base_uload_dir . "firmware/";

            if ($this->upload->do_upload('userfile')) {

                $uload_data = $this->upload->data();

                $data['firmware_name'] = 
                    $this->input->post("firmware_name");

                $data['version'] = $this->input->post("version");
                $data['comment'] = $this->input->post("comment");
                $data['installation_path'] = $uload_data['full_path'];

                $this->form_model->set_firmwareupdate_form($data);

            } else {

                show_error($this->upload->display_errors(), 500);

            }


        }
    }

    public function form($form_name) {

        $json_data = json_decode($this->input->post('data'), true);

        if (empty($json_data)) {

            $this->error_json_data_empty();

        }

        if ( $this->equals($form_name, "account-form") ) {

            $this->form_model->set_account_form($json_data);

        } else if ( $this->equals($form_name, "datetime-form") ) {


        } else if ( $this->equals($form_name, "cronjob-form") ) {

            $this->form_model->set_cronjob_form($json_data);


        } else if ( $this->equals($form_name, "network-form") ) {

            $this->form_model->set_network_form($json_data);


        } else if ( $this->equals($form_name, "portforwarding-form") ) {

            $this->form_model->set_portforwarding_form($json_data);


        } else if ( $this->equals($form_name, "settings-form") ) {


        } else {

            $this->error_form_not_exists();

        }
        print_r( $json_data);

    }

    private function error_form_not_exists() {

        show_404();

    }

    private function error_json_data_empty() {

        echo "error empty";
        show_404();

    }

    private function error_not_authenticate() {

        show_404();

    }
}
