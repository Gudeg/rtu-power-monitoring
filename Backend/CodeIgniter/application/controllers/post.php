<?php

class Post extends CI_Controller {

    public function __construct() {

        parent::__construct();
        $this->load->model("form_model");
        $this->output->enable_profiler(false);

    }

    public function form($form_name) {

        $data = json_decode($this->input->post('data'));

    }
}
