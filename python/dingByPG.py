# 链接PG钉钉告警

import psycopg2
import urllib2
import json
import time

conn = psycopg2.connect(database='vap', host='127.0.0.1', port='3306', user='admin',password='123456', options="-c search_path=%s" % 'public')
cur = conn.cursor()
sql = """select 1;"""
cur.execute(sql)
table=cur.fetchall()

title, msg, region = '标题', '内容', ''
data = {
    "msgtype": "markdown",
    "markdown": {
        "title": title,
        "text": "## %s\n%s" % (title, msg)
    }
}

url = 'https://oapi.dingtalk.com/robot/send?access_token=xxx'
headers = {'Content-Type': 'application/json'}

request = urllib2.Request(url, json.dumps(data), headers=headers)
response = urllib2.urlopen(request)
print(json.loads(response.read()))

conn.close()