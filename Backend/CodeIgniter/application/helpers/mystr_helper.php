<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

/**
 *
 * Author: Yudhistira Arya <yudhistira.arya.rukmana@gmail.com>
 *
 */
if ( ! function_exists('equals') ) {

    function equals($str1, $str2) {
        if (strcmp($str1, $str2) === 0) {
            return true; 
        } else { 
            return false; 
        }
    }
}
