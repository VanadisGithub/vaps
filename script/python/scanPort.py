import socket
from concurrent.futures import ThreadPoolExecutor

def scan_port(ip, port):
    """尝试连接指定的IP和端口，若连接成功则表明端口开放"""
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.settimeout(1)
    try:
        sock.connect((ip, port))
    except (socket.timeout, ConnectionRefusedError):
        return False
    else:
        return True
    finally:
        sock.close()

def scan_ports(ip, ports, num_threads=10):
    """扫描指定的IP和端口列表，利用多线程加快扫描速度"""
    with ThreadPoolExecutor(max_workers=num_threads) as executor:
        future_to_port = {executor.submit(scan_port, ip, port): port for port in ports}
        for future in future_to_port:
            port = future_to_port[future]
            try:
                if future.result():
                    print(f"Port {port} is open.")
            except Exception as e:
                print(f"Port {port} scan failed: {e}")

def main():
    ip = '127.0.0.1'  # 目标IP
    ports = range(1, 1025)  # 要扫描的端口范围
    scan_ports(ip, ports)

if __name__ == "__main__":
    main()
