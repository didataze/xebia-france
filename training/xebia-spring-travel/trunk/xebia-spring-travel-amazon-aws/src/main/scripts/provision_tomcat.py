#!/usr/bin/env python
from urllib import urlretrieve, URLopener
from io import open
catalinaBase = '/opt/apache-tomcat'

import shutil
from time import gmtime, strftime

# BACKUP catalina.properties
src = catalinaBase + '/conf/catalina.properties'
dst = catalinaBase + '/conf/catalina-' + strftime('%Y%m%d-%H%M%S', gmtime()) + '.properties'
shutil.copy(src, dst)

print('Created backup ' + dst)

properties = {'jdbc.url':'jdbc:mysql://myhost:3306/mydb', 'jdbc.username':'root', 'jdbc.password':'root'}

f = open(src, 'ab')

f.write('\n\n')
f.write('# BEGIN OF MODIFIED BY CLOUD-INIT ' + strftime('%Y/%m/%d-%H:%M:%S', gmtime()) + '#\n')
f.write('\n')

for key, value in sorted(properties.iteritems()):
    f.write(key + '=' + value + '\n')
    
f.write('\n')
f.write('# END OF MODIFIED BY CLOUD-INIT #\n')
f.write('\n')
f.close()

print('Updated ' + src)

# DOWNLOAD WAR
proxies = {}
url = 'http://mirrors.ibiblio.org/pub/mirrors/maven2/org/eclipse/jetty/tests/test-webapp-rfc2616/7.0.2.RC0/test-webapp-rfc2616-7.0.2.RC0.war'
filename = catalinaBase + '/webapps/test-webapp-rfc2616-7.0.2.RC0.war'
URLopener(proxies).retrieve(url, filename)

print('Downloaded ' + filename)