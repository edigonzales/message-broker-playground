# message-broker-playground

```
./bin/activemq console
```

```
http://127.0.0.1:8161/admin/
```

admin/admin



## Stomp with Python

```
python3 -m venv .venv
```

```
source .venv/bin/activate
```

```
pip install stomp-py
```


how can i send messages to activemq queue with python that is durable

### Camel consumer

```
jbang app install camel@apache/camel
```

```
camel init consume_messages.java
```

```
camel run consume_messages.java --dev --repos=https://repo1.maven.org/maven2,https://jars.interlis.ch/
```

```
camel dependency update --source=consume_messages.java 
```

### Daten

```
java -jar /Users/stefan/apps/ili2gpkg-5.1.0/ili2gpkg-5.1.0.jar --dbfile sobau.gpkg --nameByTopic --defaultSrsCode 2056 --models SO_ARP_Baugis_20190612 --schemaimport
```

```
java -jar /Users/stefan/apps/ili2gpkg-5.1.0/ili2gpkg-5.1.0.jar --dbfile sobau.gpkg --models SO_ARP_Baugis_20190612 --export example_baugis.xtf
```

ili24:

```
java -jar /Users/stefan/apps/ili2gpkg-5.1.0/ili2gpkg-5.1.0.jar --dbfile sobau_24.gpkg --nameByTopic --defaultSrsCode 2056 --models SO_ARP_Baugis_20190612 --modeldir "/Users/stefan/Downloads/;https://models.geo.admin.ch" --schemaimport
```

```
java -jar /Users/stefan/apps/ili2gpkg-5.1.0/ili2gpkg-5.1.0.jar --dbfile sobau_24.gpkg  --models SO_ARP_Baugis_20190612 --modeldir "/Users/stefan/Downloads/;https://models.geo.admin.ch" --export example_baugis_24.xtf
```