<?php

class Test extends CI_Controller { 
    private static $data = array('hello'=>'world');

    public function __construct() {
        parent::__construct();
        $this->output->enable_profiler(true);
    }

    public function simple_json() { 
        $this->output->set_content_type('application/json');
        $this->output->set_output(json_encode(self::$data));
    }

    public function simple() { 
        $output = "";

        foreach ($_SERVER as $key=>$value) {
            $output .= '[' . $key . ' ' . $value . ']' .  "\n"; 
        }
        $this->output->set_content_type('text/plain');
        $this->output->set_output("$output");
    }

    public function json() {
        // JSON Format: 
        // {"totalCount": "123", 
        //  "Power Data": [
        //      {"logid": "12", "average_consumption":"123.55", 
        //       "uptime": "3.0", "date":"1999-12-31"}

        $output = array("totalCount"=>15, 
            "Power Data"=>array(
                array("logid"=>12, "average_consumption"=>123.55, 
                    "uptime"=>3.0, "date"=>"1999-12-28"),
                array("logid"=>13, "average_consumption"=>121.15, 
                    "uptime"=>1.0, "date"=>"1999-12-29"),
            ));

        $this->output->set_content_type('application/json');
        $this->output->set_output(json_encode($output));
    }

    public function paging($limit = 30) {

        $this->output->set_content_type('text/plain');
        $this->output->set_output($limit);

    }

    public function encrypt() { 

        $value = substr(sha1("20eabe5d64b0e216796e834f52d61fd0b70332fc"), 0, 10);
        $this->output->set_output($value);

    }

}
