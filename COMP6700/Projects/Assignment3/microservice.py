import os
from flask import Flask, request
import tCurve.prob as prob

app = Flask(__name__)


#-----------------------------------
#  The following code is invoked when the path portion of the URL matches 
#         /prob?tails=1&t=1.8946&n=7
#
@app.route('/prob')
def dispatch():
    try:
        parm = {}
        for key in request.args:
            parm[key] = str(request.args.get(key, ''))
        result = prob.prob(parm)
        return str(result)
    except:
        return str("internal error")


port = os.getenv('PORT', '5000')
if __name__ == "__main__":
    app.run(host='0.0.0.0', port=int(port))

