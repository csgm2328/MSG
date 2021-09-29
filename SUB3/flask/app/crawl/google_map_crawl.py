from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.chrome.options import Options 
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import Select
from bs4 import BeautifulSoup
from tqdm import tqdm
import pandas as pd
import ijson
import numpy as np
import time

#options = Options()
#options.binary_location = 'C:/Program Files (x86)/Google/Chrome/Application/chrome.exe'
chromedriver='/app/chromedriver'
chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument('--headless')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
#chromedriver = 'C:/Program Files (x86)/Google/Chrome/Application/chrome.exe'
driver = webdriver.Chrome(chromedriver,chrome_options=chrome_options)
time.sleep(1)

# 파일 읽기
# f = open(f"./data/data.json", encoding="UTF-8")
# objects = ijson.items(f, 'item')
# df = pd.DataFrame(objects)

df = pd.read_csv('/app/crawl/google_map_data/2021-09-24_부분_55391행.csv', sep=',', encoding='utf-8')
start = 18001
end = 55391
# df = df[['업소명', '소재지도로명', '업태명', '주된음식', '행정동명', '소재지전화번호']]
# df.columns = ['name', 'address', 'cate1', 'cate2', 'dong', 'phone']
# df = df.drop_duplicates(['name'], keep='first')
# # 네이버 지도 검색창에 [~동 @@식당]으로 검색해 정확도를 높여야 합니다. 검색어를 미리 설정해줍시다.
# df['cate_mix'] = df['cate1'] + df['cate2']
df = df[start:end]
#print(df)
#df = df[3:10]

df.rename(columns={"naver_keyword":"google_keyword"},inplace=True)
df.rename(columns={"naver_keyword":"google_map_url"},inplace=True)

blog_review_list = []
google_map_star_review_stars_list = []

for i, keyword in enumerate(tqdm(df['google_keyword'])):

    google_map_search_url = f"https://www.google.com/maps/search/{keyword}"
    driver.get(google_map_search_url)
#     sub_driver.get(url+"/review/ugc")
    time.sleep(1)
#     try:
#         search_store = driver.find_element_by_css_selector("pane > div > div.widget-pane-content.cYB2Ge-oHo7ed > div > div > div.siAUzd-neVct.section-scrollbox.cYB2Ge-oHo7ed.cYB2Ge-ti6hGc.siAUzd-neVct-Q3DXx-BvBYQ > div.siAUzd-neVct.section-scrollbox.cYB2Ge-oHo7ed.cYB2Ge-ti6hGc.siAUzd-neVct-Q3DXx-BvBYQ > div:nth-child(1) > div > a")
#         search_store.send_keys(Keys.ENTER)
#     except Exception as e:
#         print(e)
#         print("fail search store")
    try:
        # 별점 
#         star_review_stars = driver.find_element_by_css_selector("#pane > div > div.widget-pane-content.cYB2Ge-oHo7ed > div > div > div.siAUzd-neVct.section-scrollbox.cYB2Ge-oHo7ed.cYB2Ge-ti6hGc > div.PPCwl > div > div.jANrlb > span > ol#app-root > div > div > div.place_detail_wrapper > div.place_section.no_margin.GCwOh > div > div > div._3XpyR > div > span._1Y6hi._1A8_M > em").text
        star_review_stars = driver.find_element_by_xpath("/html/body/div[3]/div[9]/div[8]/div/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/div[2]/span/span/span").text
        
#         더보기 = driver.find_element_by_css_selector("button[aria-label*='리뷰 더보기']")
        더보기 = driver.find_element_by_xpath("/html/body/div[3]/div[9]/div[8]/div/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/div/div[1]/span[1]/span/span[1]/span[2]/span[1]/button")
        더보기.send_keys(Keys.ENTER)
        time.sleep(2)
        # 블로그 리뷰 텍스트 가져오기
        review_text_list = [] # 임시 선언

#         # 네이버 지도 블로그 리뷰 탭은 동적 웹사이트의 순서가 주문하기, 메뉴보기 등의 존재 여부로 다르기 때문에 css selector가 아니라 element 찾기로 진행
#         review_text_crawl_list = driver.find_elements_by_class_name("ODSEW-ShBeI-ShBeI-content")
#         #pane > div > div.widget-pane-content.cYB2Ge-oHo7ed > div > div > div:nth-child(35) > div > div.ODSEW-ShBeI-content > div:nth-child(3) > div.ODSEW-ShBeI-ShBeI-content
#         # find element's' 메소드를 통해 가져온 내용은 리스트로 저장되고, 리스트 타입을 풀어서(for문 사용) 임시 데이터에 모아 두어야 한다
#         for review_crawl_data in review_text_crawl_list:
#             review_text_list.append(review_crawl_data.find_elements(By.CSS_SELECTOR, 'span'))
        
#         # 그 리스트에 저장된 텍스트 (한 식당에 대한 여러 리뷰들)를 한 텍스트 덩어리로 모아(join)줍니다.
        
                
        response = BeautifulSoup(driver.page_source, 'html.parser')
        reviews = response.find_all('div', class_='ODSEW-ShBeI NIyLF-haAclf gm2-body-2')
        rev_dict = {'Review Rate': [],
            'Review Time': [],
            'Review Text' : []}
        for result in reviews:
            review_rate = result.find('span', class_='ODSEW-ShBeI-H1e3jb')["aria-label"]
            review_time = result.find('span',class_='ODSEW-ShBeI-RgZmSc-date').text
            review = result.find('span',class_='ODSEW-ShBeI-text').text
            rev_dict['Review Rate'].append(review_rate)
            rev_dict['Review Time'].append(review_time)
            rev_dict['Review Text'].append(review)
            review_text_list.append(review)
        res = pd.DataFrame(rev_dict)
#         print(res)
        review_text = ','.join(review_text_list)

        blog_review_list.append(review_text)
        google_map_star_review_stars_list.append(star_review_stars)

    # 리뷰가 없는 업체는 크롤링에 오류가 뜨므로 표기해둡니다.
    except Exception as e1:
        #print(f"{i}행 문제가 발생")
        #print(e1)
        # 리뷰가 없으므로 null을 임시로 넣어줍니다.
        blog_review_list.append('null')
        google_map_star_review_stars_list.append('null')
#         find_element(By.CSS_SELECTOR, "h1").text
        
# driver.quit()
# sub_driver.quit()


df['google_star_point'] = google_map_star_review_stars_list  # 네이버 상세페이지에서 평가한 별점 평점
df['google_review_txt'] = blog_review_list  # 네이버 상세페이지에 나온 블로그 리뷰 텍스트들

len(blog_review_list)

#%cd outputs/BERT용 data
# Bert용
import os
from datetime import datetime

df1 = df[['name', 'google_star_point','google_review_txt']]
today = str(datetime.date(datetime.today()))
file = today + '_Bert_' + str(start) + '~' + str(end) + '행.csv'

if os.path.isfile(file):
    os.remove(file)
df1.to_csv(file, encoding='utf-8')
# df.to_csv(today + '_test.csv', encoding='cp949')
print(file)
