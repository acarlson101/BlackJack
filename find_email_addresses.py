# Author: Adam Carlson
# Date: 08/10/16
# find_email_addresses.py: The purpose of this program is to scrape a website, given a url input, and print out all email addresses 
	# that appear on that website.


# widely used module to crawl websites and collect information
import bs4

# modules to help point bs4 to certain urls
import urllib
import urllib2

# models a web crawler which can find and print out emails on a web page
class WebScraper:
	
	# initialize web scraper
	def __init__(self, url):
		self.url = url # stores the domain name given (i.e "jana.com")
		self.fullURL = "https://www." + str(url) # stores the full url (i.e. "https://www.jana.com")
		html = urllib.urlopen(self.fullURL).read() # stores a pointer to the website of interest
		self.crawler = bs4.BeautifulSoup(html,'html.parser') # stores the crawler pointed to the given website
		self.pages = [] # stores the different web pages on the website
		self.emails = [] # stores all emails found
	
	# ensure link is not subdomain or external website
	def validateLink(self, link):
		
		#setup variables to help weed out links to eternal sites or subdomains
		dot = "."+str(self.url)
		wDot = "://www."+str(self.url)
		httpDot = "://"+str(self.url)
		http = "http://"
		https = "https://"
		w = "www."
		wLink = "www." + str(self.url)
		
		if dot in link and wDot not in link: # subdomain catch
			return False
		elif w in link and wLink not in link: #  catching external site with "www" start
			return False
		elif self.url in link:
			if wDot not in link and httpDot not in link: # catching external link that contains desired domain (i.e. blahjana.com)
				return False 
			else:
				return True
		elif http in link or https in link: # catching external site with "http" start
			if self.url not in link:
				return False
				
			else:
				return True
		else:
			return True
	
	# gets all the web pages on the given website
	def getAllPages(self):
	
		# loop through all found links on home page
		for link in self.crawler.find_all('a'):
			link = str(link.get('href'))
			if self.validateLink(link):
				self.pages.append(link)
		
		# find possible links through easy-to-crawl search engine	
		url = "http://search.mywebsearch.com/mywebsearch/GGmain.jhtml?searchfor=" + str(self.url) +" contact"	
		html = urllib.urlopen(url).read()	
		searchCrawl = bs4.BeautifulSoup(html,'html.parser')
		
		#look for class corresponding to search results, found by examining search engine HTML	
		results = searchCrawl.find_all("a", { "class" : "algo-title" })
		for result in results:
			link = str(result.get('href'))
			if self.validateLink(link):
				self.pages.append(link)
	
	# prints out the emails found at all the urls saved in the object in self.pages
	def returnEmails(self):
		email = "@" + str(self.url)
		for i in range(len(self.pages)):
			if i is not 0: # skip over first element due to bug in bs4's find_all('a') function returning an undetermined value
				url2 = self.pages[i]
				opener = urllib2.build_opener()
				opener.addheaders = [('User-agent', 'Mozilla/5.0')] # tried tricking jana.com to no avail
				response = opener.open(url2)
				page = response.read()
				soup = bs4.BeautifulSoup(page,'html.parser')
				anchors = soup.find_all('a')
				for a in anchors:
					if email in str(a.get('href')): 
						self.emails.append(a)
		print self.emails				
				
		
	
		
			
		
		
		
			
		
		

# running tests
def main():
	Crawl = WebScraper("jana.com")
	Crawl.getAllPages()
	Crawl.returnEmails()

if __name__ == "__main__":	
	main()			


