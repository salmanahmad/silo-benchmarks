# Silo:

    Server Software:        
    Server Hostname:        127.0.0.1
    Server Port:            8000

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   4.942 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    100000
    Total transferred:      7600000 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    20235.92 [#/sec] (mean)
    Time per request:       4.942 [ms] (mean)
    Time per request:       0.049 [ms] (mean, across all concurrent requests)
    Transfer rate:          1501.88 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.1      0       6
    Processing:     0    5   2.9      5      51
    Waiting:        0    5   2.9      5      51
    Total:          0    5   2.9      5      54

    Percentage of the requests served within a certain time (ms)
      50%      5
      66%      6
      75%      7
      80%      7
      90%      9
      95%     10
      98%     12
      99%     13
     100%     54 (longest request)


# Node

    Server Software:        
    Server Hostname:        127.0.0.1
    Server Port:            8300

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   11.849 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    100000
    Total transferred:      13900000 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    8439.83 [#/sec] (mean)
    Time per request:       11.849 [ms] (mean)
    Time per request:       0.118 [ms] (mean, across all concurrent requests)
    Transfer rate:          1145.64 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.2      0       6
    Processing:     1   12   1.2     12      27
    Waiting:        1   12   1.2     12      27
    Total:          1   12   1.2     12      27

    Percentage of the requests served within a certain time (ms)
      50%     12
      66%     12
      75%     12
      80%     13
      90%     13
      95%     13
      98%     14
      99%     15
     100%     27 (longest request)




# Node-Cluster:

    Server Software:        
    Server Hostname:        127.0.0.1
    Server Port:            8301

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   5.977 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    100000
    Total transferred:      11300000 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    16729.77 [#/sec] (mean)
    Time per request:       5.977 [ms] (mean)
    Time per request:       0.060 [ms] (mean, across all concurrent requests)
    Transfer rate:          1846.16 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.1      0       6
    Processing:     0    6   4.3      5      47
    Waiting:        0    6   4.3      5      47
    Total:          0    6   4.3      5      47

    Percentage of the requests served within a certain time (ms)
      50%      5
      66%      7
      75%      8
      80%      9
      90%     11
      95%     14
      98%     17
      99%     20
     100%     47 (longest request)


# Ruby - Thin

    Server Software:        thin
    Server Hostname:        127.0.0.1
    Server Port:            8100

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   0.687 seconds
    Complete requests:      5000
    Failed requests:        2500
       (Connect: 0, Receive: 0, Length: 2500, Exceptions: 0)
    Write errors:           0
    Keep-Alive requests:    2500
    Total transferred:      307500 bytes
    HTML transferred:       32500 bytes
    Requests per second:    7273.85 [#/sec] (mean)
    Time per request:       13.748 [ms] (mean)
    Time per request:       0.137 [ms] (mean, across all concurrent requests)
    Transfer rate:          436.86 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.6      0       6
    Processing:     1   13  11.8     11      37
    Waiting:        0   12  12.7     11      37
    Total:          1   13  12.0     12      37

    Percentage of the requests served within a certain time (ms)
      50%     12
      66%     22
      75%     24
      80%     26
      90%     30
      95%     31
      98%     32
      99%     34
     100%     37 (longest request)


# Ruby - Puma

    Server Software:        
    Server Hostname:        127.0.0.1
    Server Port:            8101

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   2.968 seconds
    Complete requests:      10000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    10000
    Total transferred:      1020000 bytes
    HTML transferred:       130000 bytes
    Requests per second:    3368.99 [#/sec] (mean)
    Time per request:       29.682 [ms] (mean)
    Time per request:       0.297 [ms] (mean, across all concurrent requests)
    Transfer rate:          335.58 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.4      0       5
    Processing:     0   23 219.2      2    2961
    Waiting:        0   22 219.3      2    2961
    Total:          0   23 219.6      2    2963

    Percentage of the requests served within a certain time (ms)
      50%      2
      66%      3
      75%      3
      80%      3
      90%      4
      95%     10
      98%     11
      99%     14
     100%   2963 (longest request)


# Go

    Server Software:        
    Server Hostname:        127.0.0.1
    Server Port:            8700

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   8.510 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    100000
    Total transferred:      15400000 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    11751.07 [#/sec] (mean)
    Time per request:       8.510 [ms] (mean)
    Time per request:       0.085 [ms] (mean, across all concurrent requests)
    Transfer rate:          1767.25 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.1      0       6
    Processing:     1    8   4.7      8     473
    Waiting:        1    8   4.7      8     473
    Total:          1    9   4.7      8     475

    Percentage of the requests served within a certain time (ms)
      50%      8
      66%      8
      75%      9
      80%      9
      90%     10
      95%     12
      98%     12
      99%     14
     100%    475 (longest request)

# Go - MultiThreaded

    Server Software:        
    Server Hostname:        127.0.0.1
    Server Port:            8701

    Document Path:          /
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   5.141 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    100000
    Total transferred:      15400000 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    19450.23 [#/sec] (mean)
    Time per request:       5.141 [ms] (mean)
    Time per request:       0.051 [ms] (mean, across all concurrent requests)
    Transfer rate:          2925.13 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.2      0       7
    Processing:     0    5   3.2      5     107
    Waiting:        0    5   3.2      5     107
    Total:          0    5   3.3      5     111

    Percentage of the requests served within a certain time (ms)
      50%      5
      66%      5
      75%      6
      80%      6
      90%      8
      95%      9
      98%     11
      99%     13
     100%    111 (longest request)



# Nginx (1 Worker)

    Server Software:        nginx/1.4.4
    Server Hostname:        127.0.0.1
    Server Port:            8080

    Document Path:          /hello.html
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   7.466 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    99028
    Total transferred:      24695140 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    13394.33 [#/sec] (mean)
    Time per request:       7.466 [ms] (mean)
    Time per request:       0.075 [ms] (mean, across all concurrent requests)
    Transfer rate:          3230.22 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.2      0       6
    Processing:     1    7   2.5      8      73
    Waiting:        1    7   2.5      8      73
    Total:          2    7   2.5      8      73

    Percentage of the requests served within a certain time (ms)
      50%      8
      66%      8
      75%      8
      80%      8
      90%      9
      95%      9
      98%     10
      99%     14
     100%     73 (longest request)

# Nginx (OPT)

    Server Software:        nginx/1.4.4
    Server Hostname:        127.0.0.1
    Server Port:            8080

    Document Path:          /hello.html
    Document Length:        13 bytes

    Concurrency Level:      100
    Time taken for tests:   4.328 seconds
    Complete requests:      100000
    Failed requests:        0
    Write errors:           0
    Keep-Alive requests:    99044
    Total transferred:      24695220 bytes
    HTML transferred:       1300000 bytes
    Requests per second:    23106.41 [#/sec] (mean)
    Time per request:       4.328 [ms] (mean)
    Time per request:       0.043 [ms] (mean, across all concurrent requests)
    Transfer rate:          5572.44 [Kbytes/sec] received

    Connection Times (ms)
                  min  mean[+/-sd] median   max
    Connect:        0    0   0.3      0      15
    Processing:     0    4   2.9      4      41
    Waiting:        0    4   2.9      4      41
    Total:          0    4   2.9      4      43

    Percentage of the requests served within a certain time (ms)
      50%      4
      66%      5
      75%      6
      80%      6
      90%      8
      95%      9
      98%     12
      99%     14
     100%     43 (longest request)
