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

      xml = "<?xml version=\"1.0\"?>"
      xml += "<soap:Envelope xmlns:soap=\"http://www.w3.org/2001/12/soap-envelope\" soap:encodingStyle=\"http://www.w3.org/2001/12/soap-encoding\">"
      xml += "<soap:Body xmlns:m=\"/get-techniques\">"
      xml += "<m:TechniquesResponse>"
      xml += "<m:ClientId>#{clientId}</m:ClientId>"
      xml += "<m:PrimaryTube>"
      xml += "<m:Id>#{primaryTube}</m:Id>"
      xml += "</m:PrimaryTube>"
      xml += "<m:TechniquesList>"
      xml += "#{getTechniquesList()}"
      xml += "</m:TechniquesList>"
      xml += "</m:TechniquesResponse>"
      xml += "</soap:Body>"
      xml += "</soap:Envelope>"

      puts "OUTPUT: #{xml}"

      return xml

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



