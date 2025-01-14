build PACKAGE:
    mkdir -p out
    javac -d out {{replace(PACKAGE, ".", "/")}}.java

run PACKAGE:
    @just build {{PACKAGE}}
    java -cp out {{PACKAGE}}

test PACKAGE:
    @just build {{PACKAGE}}
    java -ea -cp out {{PACKAGE}}

build-server: (build "src.server.Server")
build-client: (build "src.client.Client")

run-server: (run "src.server.Server")
run-client: (run "src.client.Client")
