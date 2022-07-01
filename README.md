# hello-osgi

## 빌드하기

```
> mvn clean install
```

## Karaf Runtime에서 번들 설치하기

```
karaf@root()> bundle:install mvn:org.example.tutorial/hello-osgi/1.0.0-SNAPSHOT
Bundle ID: 57
```

## Karaf Runtime에서 번들 시작하기

```
karaf@root()> bundle:start 57
Hello, OSGi!
```

## Karaf Runtime에서 번들 중단하기

```
karaf@root()> bundle:stop 57
Goodbye, OSGi!
```

## Karaf Runtime에서 번들 제거하기

```
karaf@root()> bundle:uninstall 57
karaf@root()>
```
