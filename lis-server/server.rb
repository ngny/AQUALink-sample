require 'rubygems'
require 'sinatra'
require 'nokogiri'
require 'json'

set :bind, '0.0.0.0'





def getTechniquesList()

    max = $tests_list.length
    n_techniques_response = max-1 #Random.new.rand(0..max)
    ret = ""
    for i in 0..n_techniques_response
            n_technique = i #Random.new.rand(0..max)
            ret += "<m:TechniqueId>#{$tests_list[n_technique]}</m:TechniqueId>"
    end
   return ret
end



def getTechniques(clientId,primaryTube)



  message = <<-EOF
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/get-techniques">
      <m:TechniquesResponse>
         <m:Order>
            <m:OrderId>ORD000001</m:OrderId>
            <m:OrderStatus>OPENED</m:OrderStatus>
            <m:OrderPriority>URGENT</m:OrderPriority>
         </m:Order>
         <m:PrimaryTube>
            <m:Id>30012345678</m:Id>
            <m:ExtC>MAYC</m:ExtC>
            <m:Service>SERVICE</m:Service>
            <m:Section>SECTION</m:Section>
            <m:pType>PATIENT_TYPE</m:pType>
         </m:PrimaryTube>
         <m:CustomFields>
         <m:CF1>CF1_VALUE</m:CF1>
         <m:CF3>CF3_VALUE</m:CF3>
            <m:CF10>CF10_VALUE</m:CF10>
         </m:CustomFields>
         <m:TechniquesList>
            <m:TechniqueId>
               <m:testCode>F007EXUF</m:testCode>
               <m:testCodeStatus>PENDING</m:testCodeStatus>
            </m:TechniqueId>
            <m:TechniqueId>TEST1</m:TechniqueId>
            <m:TechniqueId>
               <m:testCode>A11285</m:testCode>
               <m:testCodeStatus>PENDING</m:testCodeStatus>
               <m:SecondaryTubeId>3001234567801</m:SecondaryTubeId>
            </m:TechniqueId>
         </m:TechniquesList>
      </m:TechniquesResponse>
   </soap:Body>
</soap:Envelope>
EOF



  message = <<-EOF
<?xml version="1.0" encoding="UTF-8"?>
<soap:Envelope xmlns:soap="http://www.w3.org/2001/12/soap-envelope" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding">
   <soap:Body xmlns:m="/get-techniques">
      <m:TechniquesResponse>
         <m:Order>
            <m:OrderId>ORD000001</m:OrderId>
            <m:OrderStatus>OPENED</m:OrderStatus>
            <m:OrderPriority>URGENT</m:OrderPriority>
         </m:Order>
         <m:PrimaryTube>
            <m:Id>30012345678</m:Id>
            <m:ExtC>MAYC</m:ExtC>
            <m:Service>SERVICE</m:Service>
            <m:Section>SECTION</m:Section>
            <m:pType>PATIENT_TYPE</m:pType>
         </m:PrimaryTube>
         <m:CustomFields>
         <m:CF1>CF1_VALUE</m:CF1>
         <m:CF3>CF3_VALUE</m:CF3>
            <m:CF10>CF10_VALUE</m:CF10>
         </m:CustomFields>
         <m:TechniquesList>
            <m:TechniqueId>TEST1</m:TechniqueId>
         </m:TechniquesList>
      </m:TechniquesResponse>
   </soap:Body>
</soap:Envelope>
EOF

  message = <<-EOF
  <?xml version="1.0" encoding="utf-8"?>
  <soap:Envelope xmlns:m="/get-techniques" soap:encodingStyle="http://www.w3.org/2001/12/soap-encoding" xmlns:soap="http://www.w3.org/2001/12/soap-envelope">
    <soap:Body>
      <m:TechniquesResponse>
        <m:ClientId>aqua7025</m:ClientId>
        <m:PrimaryTube>
          <m:Id>000000000094</m:Id>
        </m:PrimaryTube>
        <m:TechniquesList>
          <m:TechniqueId>2.16</m:TechniqueId>
          <m:TechniqueId>2.17</m:TechniqueId>
          <m:TechniqueId>ETF</m:TechniqueId>
        </m:TechniquesList>
      </m:TechniquesResponse>
    </soap:Body>
  </soap:Envelope>
EOF

  puts message
  message
end



def sendResults(clientId,primaryTube)

      xml =""
      xml += "<?xml version=\"1.0\"?>"
      xml += "<soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2001/12/soap-encoding\">"
      xml += "<soap:Body xmlns:m=\"/result-ack\">"
      xml += "<m:ResultAckResponse>"
      xml += "<m:Message>OK</m:Message>"
      xml += "<m:ClientId>#{clientId}</m:ClientId>"
      xml += "<m:PrimaryTube>"
      xml += "<m:Id>#{primaryTube}</m:Id>"
      xml += "</m:PrimaryTube>"
      xml += "</m:ResultAckResponse>"
      xml += "</soap:Body>"
      xml += "</soap:Envelope>"


      puts "OUTPUT: #{xml}"

      return xml

end




post '/get-techniques' do

    clientId = ""
    primaryTube = ""
    payload = request.body.read
    puts payload

   doc_input = Nokogiri::XML::parse payload
   doc_input.remove_namespaces!
   clientId = doc_input.css('ClientId').text
   doc_input.css("PrimaryTube").each do |item|
    primaryTube = item.css('Id').text
  end

   puts "PARSING: tube#{primaryTube} clientId:#{clientId}"
   getTechniques(clientId,primaryTube)
end


post '/send-results' do
  puts "SEND RESULTS PAYLOAD"
    clientId = ""
    primaryTube = ""
    payload = request.body.read
    puts payload
 doc_input = Nokogiri::XML::parse payload
   doc_input.remove_namespaces!
   clientId = doc_input.css('ClientId').text
   doc_input.css("PrimaryTube").each do |item|
    primaryTube = item.css('Id').text
  end

   puts "PARSING: tube#{primaryTube} clientId:#{clientId}"
   sendResults(clientId,primaryTube)
end



     file = File.read('config.json')
     data_hash = JSON.parse(file)


     #puts data_hash["tests"]
     $tests_list = data_hash["tests"]
