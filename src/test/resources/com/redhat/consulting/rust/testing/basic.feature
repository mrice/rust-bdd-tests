Feature: the site should be up and operational

  Scenario: the site should load
    Given that I have a web browser
    When I access "http://p1-projectrust.rhcloud.com/"
    Then the browser should load a page titled "RUST"

