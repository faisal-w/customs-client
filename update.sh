#!/bin/bash

echo 'Mengambil data dari server...'
git pull origin master

case $1 in
'and')
	case $2 in
	'run')
		. run.sh
		;;
	esac
	;;
esac
