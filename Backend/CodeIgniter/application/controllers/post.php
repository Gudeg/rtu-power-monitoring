<?php

class Post extends CI_Controller {

    public function __construct() {
        parent::__construct();
        $this->output->enable_profiler(false);
    }

    public function form($form_name) {

        $this->output->set_content_type("text/plain");
        $this->output->set_output($form_name);
    }
}
