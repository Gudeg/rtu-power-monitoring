abstract class ScriptUpdate { 
    private $command; 
    private $path; 
    private $global_script_path;
    private $restart_command; 

    private function save() { 
        if (! $handle = fopen($this->$path, "w+")) {
            return false; 
        }
        fwrite($handle, $this->command); 
        fclose($handle);
        return true;
    }

    private function restart() {
        if (! $handle = fopen($this->$global_script_path, "w+")) {
            return false; 
        } 
        fwrite($handle, $this->restart_command); 
        fclose($handle);
        return true; 

    }

    private function validate() {

        return true; 
    } 

    public function update($script, $path=null, $global_script_path=null) {

        if( !$this->validate($script) ) {
            return false;
        }

        rw_timer();

        $this->command = $script; 

        if ( !$path === null ) { 
            $this->path = $path; 
        }

        if ( !$global_script_path === null) {
            $this->global_script_path = $global_script_path;
        }

        if ( !$this->save() ) {
            return false; 
        }

        $this->restart();
        return true; 
    }
}

class CronSettings extends ScriptUpdate {

    private __construct() {
        $this->$restart_command ='#!/bin/sh
		/bin/killall crond > /dev/null 2 >&1
		/bin/cp /htdocs/ipvision-client/ipvision/config/cron/root /var/spool/cron/crontabs/
		/usr/sbin/crond > /dev/null 2 >&1
		';
        
        $this->path = "/etc/cron.cairo.pm";

        if ( defined(SCRIPT_FILE) ) {
            $this->$global_script_path = SCRIPT_FILE;
        }
    }

}

class RinetdSettings extends ScriptUpdate {

    private __construct() {
        $this->$restart_command ='#!/bin/sh
		/etc/init.d/rinetd stop > /dev/null 2 >&1
		/etc/init.d/rinetd start > /dev/null 2 >&1
		';

        $this->path = "/etc/rinetd.conf";

        if ( defined(SCRIPT_FILE) ) {
            $this->$global_script_path = SCRIPT_FILE;
        }
    }
}
