<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/**
 *
 * Author: Yudhistira Arya <yudhistira.arya.rukmana@gmail.com>
 *
 */

if ( ! function_exists('rw') ) {

    function rw() { 


        $command = escapeshellcmd("rw");
        exec($command, $output, $status);


    }

}

if ( ! function_exists('ro') ) {

    function ro() {

        $command = escapeshellcmd("ro");
        exec($command, $output, $status);

    }

}

if ( ! function_exists('rwtimer') ) {

    function save_rw($callback) {
        if ( is_callable($callback) ) {
            rw();
            $result = $callback();
            ro();

            if ($result !== null) {
                return $result; 
            } else {
                return $null;
            }
        }
    }
}

if ( ! function_exists('write_ini_file') ) {

    function write_ini_file($assoc_arr, $path, $has_sections=FALSE) {
        $content = ""; 
        if ($has_sections) { 
            foreach ($assoc_arr as $key=>$elem) { 
                $content .= "\n[".$key."]\n"; 
                foreach ($elem as $key2=>$elem2) { 
                    if(is_array($elem2)) 
                    { 
                        for($i=0;$i<count($elem2);$i++) 
                        { 
                            $content .= $key2."[] = \"".$elem2[$i]."\"\n"; 
                        } 
                    } 
                    else if($elem2=="") $content .= $key2." = \n"; 
                    else $content .= $key2." = \"".$elem2."\"\n"; 
                } 
            } 
        } 
        else { 
            foreach ($assoc_arr as $key=>$elem) { 
                if(is_array($elem)) 
                { 
                    for($i=0;$i<count($elem);$i++) 
                    { 
                        $content .= $key."[] = \"".$elem[$i]."\"\n"; 
                    } 
                } 
                else if($elem=="") $content .= $key." = \n"; 
                else $content .= $key." = \"".$elem."\"\n"; 
            } 
        } 

        if (!$handle = fopen($path, 'w')) { 
            return false; 
        } 
        if (!fwrite($handle, $content)) { 
            fclose($handle);
            return false; 
        } 
        fclose($handle); 
        return true; 
    }
}

if ( ! function_exists('update_ini_file') ) {

    function update_ini_file($indices, $data, $path) {

        foreach($data as $key=>$value) {
            $data[$key] = trim($value);
        }

        $_data = parse_ini_file($path, true);
        $_data[$indices] = $data;

        rw();
        $result = write_ini_file($_data, $path, true); 
        ro(); 

        return $result;
    }
}

if ( ! function_exists('parse_ini_to_json') ) {

    function parse_ini_to_json($path, $indices) {

        $ini = parse_ini_file($path, true);
        return json_encode($ini[$indices]);

    }
}
