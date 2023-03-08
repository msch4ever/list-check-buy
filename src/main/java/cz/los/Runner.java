package cz.los;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Runner {

    public static void main(String[] args) {
        String logo =
                "    __    _      __        ________              __         ____             \n" +
                        "   / /   (_)____/ /_      / ____/ /_  ___  _____/ /__      / __ )__  ____  __\n" +
                        "  / /   / / ___/ __/_____/ /   / __ \\/ _ \\/ ___/ //_/_____/ __  / / / / / / /\n" +
                        " / /___/ (__  ) /_/_____/ /___/ / / /  __/ /__/ ,< /_____/ /_/ / /_/ / /_/ / \n" +
                        "/_____/_/____/\\__/      \\____/_/ /_/\\___/\\___/_/|_|     /_____/\\__,_/\\__, /  \n" +
                        "                                                                    /____/   ";
        log.info("Initializing app...\n{}", logo);
    }

}
