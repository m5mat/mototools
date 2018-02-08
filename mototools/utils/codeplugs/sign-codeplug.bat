@echo off

set CTB_KEY='RmFAUjxmbWpLNmkqUUVWfUhoQ1Usd1BOP0ZpLVg7USQ='
set CTB_IV='czp8KVtQfVU/bj9BbVBnIw=='

for %%f in (MotoTools*.xml) do (
        echo Signing %%~nf
        codeplug build -o %%~nf-%date:~-4,4%%date:~-7,2%%date:~-10,2%.ctb %%~nf.xml ../yourkey.pem
)