<?php 

class upload extends CI_Controller { 

    public function __construct() {

        parent::__construct(); 
        $this->load->model("user_model");

        $this->user_model->check_session() ? 
            $this->load->library('upload') : show_404;
    }

    public function firmware() { 

        $config['upload_path'] = '../models/uploads/firmware/'; 
        $config['overwrite'] = true; 

        $this->upload->initialize($config);

        if ( $this->upload->do_upload() ) { 

            $uploaded_data = json_encode($this->upload->data(), true);
            $this->output->set_content_type('application/json');
            $this->output->set_output($uploaded_data);

        } else { 

            $error = $this->upload->display_errors();
            show_error($error, 500);

        }
    }
}
