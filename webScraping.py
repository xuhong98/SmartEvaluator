from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from bs4 import BeautifulSoup
from urllib.request import urlopen
from xlwt import Workbook
import xlrd
import requests


chrome_options = Options()

driver = webdriver.Chrome(chrome_options=chrome_options)


driver.get("https://www.99.co/singapore/sale/condos-apartments?gclid=Cj0KCQjw5s3cBRCAARIsAB8ZjU0qFruEVAYmOpezo095KxdvcDOwtJKQqZ13IYevDgzwjNHHw1WvaQYaAmPqEALw_wcB&listing_type=sale&main_category=condo&map_bounds=1.5827095153768858%2C103.49449749970108%2C1.1090706240313446%2C104.12483807587296&page_num=1&page_size=30&property_segments=residential&query_coords=1.3039947%2C103.8298507&query_limit=radius&query_type=city&radius_max=1000&rental_type=unit&show_future_mrts=true&zoom=11")

wb = Workbook()

writesheet = wb.add_sheet('condoInfoSample')

for j in range(499):

    driver.get(driver.current_url)

    links = driver.find_elements_by_class_name('ListingItem-innerWrapper')

    names = driver.find_elements_by_class_name('ListingItem-title')

    addresses = driver.find_elements_by_class_name('location')

    prices = driver.find_elements_by_class_name('ListingItem-price')

    unitprices = driver.find_elements_by_class_name('area-price')

    sizes = driver.find_elements_by_class_name('sqft')

    buttons = driver.find_elements_by_class_name('next')

    b = buttons[0]

    for button in buttons:
        if (button.text == 'Next'):
            b=button
            break

    #button.click()

    #print(len(addresses))

    for i in range(60):
        address = addresses[i].text.split('·')[0]

        address = addresses[i].text.split('·')[0]
        adword = address.split(" ")
        strtoget = "https://maps.googleapis.com/maps/api/geocode/json?address="+adword[0]

        for aw in adword[1:-1]:
           strtoget += ("+"+aw)
        strtoget = strtoget+",+Singapore"

        response = requests.get(strtoget)

        resp_json_payload = response.json()

        try:
            print(resp_json_payload['results'][0]['geometry']['location']['lat'])
            writesheet.write(j * 60 + i, 6, resp_json_payload['results'][0]['geometry']['location']['lat'])
            print(resp_json_payload['results'][0]['geometry']['location']['lng'])
            writesheet.write(j * 60 + i, 7, resp_json_payload['results'][0]['geometry']['location']['lng'])
        except:
            pass

        link = links[i].get_attribute("href")
        print(names[i].text+"   "+address + "  " + prices[i].text + "  " + unitprices[i].text + "  " + sizes[i].text + "  ")
        print(link)
        writesheet.write(j*60+i, 0, names[i].text)
        writesheet.write(j*60+i, 1, address)
        writesheet.write(j*60+i, 2, prices[i].text)
        writesheet.write(j*60+i, 3, unitprices[i].text)
        writesheet.write(j*60+i, 4, sizes[i].text)
        writesheet.write(j * 60 + i, 5, link)

        print("\n")

    b.click()

driver.close()

wb.save('housingInfoSample.xls')







'''
book = xlrd.open_workbook("data.xlsx")

wb = Workbook()

writesheet = wb.add_sheet('sheet2towrite')

writesheet3 = wb.add_sheet('sheet3towrite')

readsheet = book.sheet_by_index(1)

to_traverse = list(range(96))

current_a_record_row = 2

current_aaaa_record_row = 2

current_domain_row = 2

current_row = 2

for c in to_traverse:

    print("Now is row "+str(c+701)+"\n")

    chrome_options = Options()

    driver = webdriver.Chrome(chrome_options=chrome_options)

    row_to_write = []

    for g in range(5):
        row_to_write.append(readsheet.cell(c+702,g+1).value)

    string_to_search = row_to_write[1]

    driver.get("https://securitytrails.com/")

    inputElement = driver.find_element_by_tag_name("input")

    inputElement.send_keys(string_to_search)

    button = driver.find_element_by_xpath("//button[@type='submit']")

    button.click()

    print(driver.current_url)

    driver.get(driver.current_url)

    html = urlopen(driver.current_url).read().decode('utf-8')

    soup = BeautifulSoup(html, "html.parser")

    tbodies = soup.find_all("tbody")

    indexes = [0, 1]

    #a_record_found = "true"

    #aaaa_record_found = "true"

    written = "false"

    if(len(tbodies)>0):
        for i in indexes:  # only in A and AAAA box
            trs = tbodies[i].find_all("tr")
            for tr in trs:
                tds = tr.find_all("td")

                if (len(tds) == 1):
                    print("No records")

                elif ((tds[1].text) != ''):
                    written = "true"
                    if (i == 0):
                        for column_to_write in range(5):
                            writesheet.write(current_row, column_to_write + 1, row_to_write[column_to_write])
                        writesheet.write(current_row, 6, tds[0].text)
                        writesheet.write(current_row, 7, tds[1].text)
                        current_row += 1
                        print("A Records")
                    else:
                        for column_to_write in range(5):
                            writesheet.write(current_row, column_to_write + 1, row_to_write[column_to_write])
                        writesheet.write(current_row, 8, tds[0].text)
                        writesheet.write(current_row, 9, tds[1].text)
                        current_row += 1
                        print("AAAA Records")

                    # previous 2 lines not meet supposed aim, acctually got aligning issue, but can be adjusted

                    print(tds[0].text)  # This is the name
                    print(tds[1].text)  # This is the count

        if (written == "false"):
            for column_to_write in range(5):
                writesheet.write(current_row, column_to_write + 1, row_to_write[column_to_write])
            current_row += 1

        # now deals with sheet 3

        writesheet3.write(current_domain_row, 1, row_to_write[0])
        writesheet3.write(current_domain_row, 2, row_to_write[1])

        driver.get("https://securitytrails.com/list/apex_domain/" + row_to_write[1] + "/")

        driver.get(driver.current_url)

        html = urlopen(driver.current_url).read().decode('utf-8')

        soup = BeautifulSoup(html, "html.parser")

        tbody = soup.find("tbody")

        trs = tbody.find_all("tr")

        if (len(trs) == 0):
            current_domain_row += 1

        else:
            for tr in trs:
                tds = tr.find_all("td")
                writesheet3.write(current_domain_row, 3, tds[0].text)
                print(tds[0].text)
                writesheet3.write(current_domain_row, 4, tds[2].text)
                print(tds[2].text)
                writesheet3.write(current_domain_row, 5, tds[3].text)
                print(tds[3].text)
                current_domain_row += 1

    driver.close()

wb.save('writtenX12.xls')



'''




