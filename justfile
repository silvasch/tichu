build PACKAGE:
    mkdir -p out
    javac -d out {{replace(PACKAGE, ".", "/")}}.java

run PACKAGE *ARGS:
    @just build {{PACKAGE}}
    java -cp out {{PACKAGE}} {{ARGS}}

test PACKAGE:
    @just build {{PACKAGE}}
    java -ea -cp out {{PACKAGE}}

build-server: (build "src.server.Server")
build-client: (build "src.client.Client")

run-server *ARGS:
    @just run "src.server.Server" {{ARGS}}

run-client *ARGS:
    @just run "src.client.Client" {{ARGS}}

tmux-session IP="localhost" PORT="3000":
    tmux new-session -d -s "Tichu" -n "server" "just run-server {{PORT}}"
    tmux set-option remain-on-exit on
    sleep 1
    tmux new-window -n "client one" "just run-client {{IP}} {{PORT}} 1"
    tmux set-option remain-on-exit on
    sleep 1
    tmux new-window -n "client two" "just run-client {{IP}} {{PORT}} 2"
    tmux set-option remain-on-exit on
    sleep 1
    tmux new-window -n "client three" "just run-client {{IP}} {{PORT}} 3"
    tmux set-option remain-on-exit on
    sleep 1
    tmux new-window -n "client four" "just run-client {{IP}} {{PORT}} 4"
    tmux set-option remain-on-exit on
    tmux select-window -t 0
    tmux attach

format:
    find . -type f -name "*.java" | xargs google-java-format -r
