# 概要

ワンタイム URL を生成するサンプル。

# 使い方

1. アプリケーションを起動する。
```
gradle bootRun
```


2. URL を生成する。
```
curl http://localhost:8080/encode/sample.csv

http://localhost:8080/decode/2vp6cZNN4DW1NPs2_ViOx1J2gfX_KMDtDdKaoq-jLLdqdCIgo0zBO9eJKyH0bYdk
```

3. 生成された URL に 1 分以内にアクセスする。
```
curl http://localhost:8080/decode/2vp6cZNN4DW1NPs2_ViOx1J2gfX_KMDtDdKaoq-jLLdqdCIgo0zBO9eJKyH0bYdk

sample.csv
```

4. 1 分経過後に同じ URL にアクセスすると 410 GONE が返ってくる。
```
curl http://localhost:8080/decode/2vp6cZNN4DW1NPs2_ViOx1J2gfX_KMDtDdKaoq-jLLdqdCIgo0zBO9eJKyH0bYdk

{"timestamp":"2022-02-02T09:13:09.997+00:00","status":410,"error":"Gone","path":"/decode/2vp6cZNN4DW1NPs2_ViOx1J2gfX_KMDtDdKaoq-jLLdqdCIgo0zBO9eJKyH0bYdk"}
```
