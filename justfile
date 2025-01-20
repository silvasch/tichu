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

tmux-session:
    tmux new-session -d -s "Tichu" -n "server" "just run-server"
    tmux set-option remain-on-exit on
    tmux new-window -n "client one" "just run-client"
    tmux set-option remain-on-exit on
    tmux new-window -n "client two" "just run-client"
    tmux set-option remain-on-exit on
    tmux new-window -n "client three" "just run-client"
    tmux set-option remain-on-exit on
    tmux new-window -n "client four" "just run-client"
    tmux set-option remain-on-exit on
    tmux attach

format:
    find . -type f -name "*.java" | xargs google-java-format -r
