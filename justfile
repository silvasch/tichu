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
