# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re, subprocess

class UntitledTestCase(unittest.TestCase):
    def setUp(self):
        self.options = Options()
        self.options.set_headless(headless=True)
        self.driver = webdriver.Firefox(firefox_options=self.options)
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.katalon.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
        self.vm_url = "http://" + str(subprocess.check_output(["docker-machine","url", "parcel-test"])).split(':')[1].split('/')[2]
        self.correct_result = "XS"

    def test_untitled_test_case(self):
        driver = self.driver
        driver.get(self.vm_url)
        driver.find_element_by_id("cfg-size-length").click()
        driver.find_element_by_id("cfg-size-length").clear()
        driver.find_element_by_id("cfg-size-length").send_keys("1")
        driver.find_element_by_id("cfg-size-height").clear()
        driver.find_element_by_id("cfg-size-height").send_keys("2")
        driver.find_element_by_id("cfg-size-depth").clear()
        driver.find_element_by_id("cfg-size-depth").send_keys("3")
        driver.find_element_by_id("cfg-size-btn").click()
        time.sleep(5)
        result = driver.find_element_by_id("cfg-category").text.split()[1]
        if(result != self.correct_result):
            exit(1)


    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True

    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True

    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True

    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
