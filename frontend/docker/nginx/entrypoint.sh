#!/bin/sh

echo "Replace env vars"
for file in /usr/share/dist/js/app.*.js;
do
  echo "Processing $file ..";

  if [ ! -f $file.tmpl.js ]; then
      cp $file $file.tmpl.js
  fi

  # shellcheck disable=SC2016
  envsubst '$VUE_APP_TEST_KEY,$VUE_APP_TEST_KEY_2' < $file.tmpl.js > $file
done

echo "Starting Nginx"
nginx -g 'daemon off;'