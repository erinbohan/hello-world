mkdir -p log

java -jar hello-mongo-0.0.1-SNAPSHOT.jar 2 >> log/rest-shorturl.err 1 >> log/rest-shorturl.stdout
