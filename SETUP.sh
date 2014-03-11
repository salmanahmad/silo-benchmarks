
# Increase Port Range (Default: 49152)
sudo sysctl -w net.inet.ip.portrange.first=32768

# Increase MSL and TIME_WAIT (Default: 15000)
sudo sysctl -w net.inet.tcp.msl=1000

# Increase ulimit
sudo ulimit -n 10240

# Taken from Benchmarking Python Servers Link
#echo "10152 65535" > /proc/sys/net/ipv4/ip_local_port_range
#sysctl -w fs.file-max=128000
#sysctl -w net.ipv4.tcp_keepalive_time=300
#sysctl -w net.core.somaxconn=250000
#sysctl -w net.ipv4.tcp_max_syn_backlog=2500
#sysctl -w net.core.netdev_max_backlog=2500
#ulimit -n 10240


## To pass arguments into a Makefile
## make action argument=something
#echo:
#    echo $(argument)

