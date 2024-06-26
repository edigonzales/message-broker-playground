import stomp

# Create a connection to ActiveMQ
conn = stomp.Connection12([('localhost', 61613)], auto_content_length=False) #https://github.com/jasonrbriggs/stomp.py/issues/216
conn.connect(login='admin', passcode='admin', wait=True)

# Send a message to a specific destination (queue)
destination = 'ch/so/dsbjd/ebau'
message = '''<?xml version="1.0" encoding="UTF-8"?>
<ili:transfer xmlns:ili="http://www.interlis.ch/xtf/2.4/INTERLIS" xmlns:geom="http://www.interlis.ch/geometry/1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:SO_ARP_Baugis_20190612="http://www.interlis.ch/xtf/2.4/SO_ARP_Baugis_20190612">
  <ili:headersection>
    <ili:models>
      <ili:model>SO_ARP_Baugis_20190612</ili:model>
    </ili:models>
    <ili:sender>ili2gpkg-5.1.0-58fc980cc6639b2c16a3cff8a0fa19ef1484b11c</ili:sender>
  </ili:headersection>
  <ili:datasection>
    <SO_ARP_Baugis_20190612:Baugis ili:bid="SO_ARP_Baugis_20190612.Baugis">
      <SO_ARP_Baugis_20190612:Geschaeft ili:tid="E888E057-B203-4488-866F-0EA1D365AFEF">
        <SO_ARP_Baugis_20190612:SobauID_sprechend>Neubau Zürihaus</SO_ARP_Baugis_20190612:SobauID_sprechend>
        <SO_ARP_Baugis_20190612:SobauID_system>08DE6290-A2EF-4044-9C49-3B9A1F280610</SO_ARP_Baugis_20190612:SobauID_system>
        <SO_ARP_Baugis_20190612:Art_Text>foo</SO_ARP_Baugis_20190612:Art_Text>
        <SO_ARP_Baugis_20190612:Art_Code>1</SO_ARP_Baugis_20190612:Art_Code>
        <SO_ARP_Baugis_20190612:Vorhaben>Gaga</SO_ARP_Baugis_20190612:Vorhaben>
        <SO_ARP_Baugis_20190612:Entscheid_Text>Ja</SO_ARP_Baugis_20190612:Entscheid_Text>
        <SO_ARP_Baugis_20190612:Entscheid_Code>1</SO_ARP_Baugis_20190612:Entscheid_Code>
        <SO_ARP_Baugis_20190612:Sistiert_Text>nein</SO_ARP_Baugis_20190612:Sistiert_Text>
        <SO_ARP_Baugis_20190612:Sistiert_Code>2</SO_ARP_Baugis_20190612:Sistiert_Code>
        <SO_ARP_Baugis_20190612:Sobau_Link>https://agi.so.ch</SO_ARP_Baugis_20190612:Sobau_Link>
        <SO_ARP_Baugis_20190612:Projektleiter_Name>Lisa Liegenschaft</SO_ARP_Baugis_20190612:Projektleiter_Name>
        <SO_ARP_Baugis_20190612:Geometrie>
          <geom:coord>
            <geom:c1>2607892.464</geom:c1>
            <geom:c2>1228257.773</geom:c2>
          </geom:coord>
        </SO_ARP_Baugis_20190612:Geometrie>
      </SO_ARP_Baugis_20190612:Geschaeft>
    </SO_ARP_Baugis_20190612:Baugis>
  </ili:datasection>
</ili:transfer>
'''

conn.send(body=message, destination=destination, headers={'persistent' :'true'})

# Disconnect from ActiveMQ
conn.disconnect()
