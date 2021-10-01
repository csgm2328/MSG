FROM python:3.8.10
ADD . /app 
WORKDIR /app 
RUN python3 -m pip install -U pip 
RUN pip3 install flask 
RUN pip3 install uwsgi
RUN pip3 install numpy
RUN pip3 install pandas
RUN pip3 install tqdm
RUN pip3 install ijson
RUN pip3 install selenium
RUN pip3 install bs4
RUN pip3 install powernad
RUN pip3 install jsonpickle
RUN pip3 install python-dotenv
RUN pip3 install ipywidgets

CMD uwsgi uwsgi.ini
