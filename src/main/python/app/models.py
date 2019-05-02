import turicreate as tc

# constants
data_folder = 'app/data'
model_folder = 'app/trained_models'

class ItemRecommender:
    def trainModels(self):
        # Load the data (From an S3 bucket)
        data = tc.SFrame(data_folder + '/trx_data.csv')

        # # Make sure the target is discrete
        # >>> data['is_expensive'] = data['price'] > 30000

        print('Creating model')
        model = tc.logistic_classifier.create(
            data, target='actionTaken', features=['userDistance'])

        print("Coefficients...")
        print(model.coefficients)

        test = tc.SFrame({'userDistance': [0, 5, 11], })

        print("Predictions...")
        # get out true or false for each
        print(model.predict(test))

        # get
        print(model.predict(data, output_type='probability'))
        print(model.predict(data, output_type='margin'))

        print("Evaluations...")
        print(model.evaluate(data))

        model.save(model_folder + '/' + 'smart-tours-logistic')
